package com.ags.pirate;

import com.ags.pirate.listener.DoubleClickListener;
import com.ags.pirate.model.Serie;
import com.ags.pirate.model.Torrent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Application with Swing as GUI.
 * @author Angel
 * @since 17/11/13
 */
public class PirateGui extends AbstractPirate {

    private static Logger LOGGER = LoggerFactory.getLogger(Pirate.class);

    private void execute() {
        //Create and set up the window.
        final JFrame frame = new JFrame("PirateBay downloader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout layout = new FlowLayout();
        frame.setLayout(layout);


        final List<Serie> series = getSeries(false);
        final JList<Serie> seriesAvailable = new JList<Serie>(series.toArray(new Serie[series.size()]));
        final JPanel seriesPanel = new JPanel();
        final JPanel torrentPanel = new JPanel();
        frame.getContentPane().add(seriesPanel);
        frame.getContentPane().add(torrentPanel);
        seriesPanel.add(seriesAvailable);


        seriesAvailable.addMouseListener(new DoubleClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Serie selectedSerie = seriesAvailable.getSelectedValue();
                    List<Torrent> torrents = getTorrents(false, selectedSerie);
                    if (torrents.size() == 0) {
                        //TODO add alert message
                        LOGGER.warn("no torrents found!");
                    }
                    final JList<Torrent> torrentsAvailable = new JList<Torrent>(torrents.toArray(new Torrent[torrents.size()]));
                    torrentsAvailable.addMouseListener(new DoubleClickListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getClickCount() == 2) {
                                Torrent selectedTorrent = torrentsAvailable.getSelectedValue();
                                downloadTorrent(selectedTorrent);
                            }
                        }
                    });
                    torrentPanel.removeAll();
                    torrentPanel.add(torrentsAvailable);
                    frame.pack();
                }
            }
        });


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PirateGui().execute();
            }
        });
    }
}
