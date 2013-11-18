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
 *
 * @author Angel
 * @since 17/11/13
 */
public class PirateGui extends AbstractPirate {

    private static Logger LOGGER = LoggerFactory.getLogger(Pirate.class);

    private void execute() {
        //Create and set up the window.
        final JFrame frame = new JFrame("PirateBay downloader");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout(2, 2);
        frame.getContentPane().setLayout(layout);


        final JPanel infoPanel = new JPanel();
        frame.add(infoPanel, BorderLayout.SOUTH);


        final List<Serie> series = getSeries(false);
        final JList<Serie> seriesAvailable = new JList<Serie>(series.toArray(new Serie[series.size()]));
        final JPanel seriesPanel = new JPanel();
        final JPanel torrentPanel = new JPanel();
        frame.add(seriesPanel, BorderLayout.WEST);
        seriesPanel.add(seriesAvailable);
        frame.add(torrentPanel, BorderLayout.EAST);


        seriesAvailable.addMouseListener(new DoubleClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    final Serie selectedSerie = seriesAvailable.getSelectedValue();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {

                            List<Torrent> torrents = getTorrents(false, selectedSerie);
                            if (torrents.size() == 0) {
                                //TODO add alert message
                                LOGGER.warn("no torrents found!");
                            }
                            final JList<Torrent> torrentsAvailable = new JList<Torrent>(torrents.toArray(new Torrent[torrents.size()]));
                            infoPanel.removeAll();
                            infoPanel.add(new JTextArea("Found "+torrents.size()+" torrent(s)"));
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
                    };
                    new Thread(runnable).start();

                    infoPanel.removeAll();
                    infoPanel.add(new JTextArea("Searching torrents for "+selectedSerie));
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
