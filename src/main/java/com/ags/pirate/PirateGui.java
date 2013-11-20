package com.ags.pirate;

import com.ags.pirate.event.SerieSelectedEvent;
import com.ags.pirate.event.TorrentSelectedEvent;
import com.ags.pirate.gui.SeriesList;
import com.ags.pirate.gui.TorrentList;
import com.ags.pirate.listener.DoubleClickListener;
import com.ags.pirate.listener.SerieSelectedListener;
import com.ags.pirate.listener.TorrentSelectedListener;
import com.ags.pirate.model.Serie;
import com.ags.pirate.model.Torrent;
import net.miginfocom.swing.MigLayout;
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
        MigLayout layout = new MigLayout("", "[][]", "[]");
        frame.getContentPane().setLayout(layout);


        final JPanel infoPanel = new JPanel();
        frame.add(infoPanel, BorderLayout.SOUTH);


        final List<Serie> series = getSeries(false);
        final SeriesList seriesAvailable = new SeriesList(series.toArray(new Serie[series.size()]));
        final JPanel seriesPanel = new JPanel();
        final JPanel torrentPanel = new JPanel();
        frame.add(seriesPanel, BorderLayout.WEST);
        seriesPanel.add(seriesAvailable);
        frame.add(torrentPanel, BorderLayout.EAST);

        seriesAvailable.setSerieSelectedListener(new SerieSelectedListener() {
            @Override
            public void actionPerformed(final SerieSelectedEvent event) {
                //different thread to update the UI
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        List<Torrent> torrents = getTorrents(false, event.getSerieSelected());
                        if (torrents.size() == 0) {
                            //TODO add alert message
                            LOGGER.warn("no torrents found!");
                        }
                        TorrentList torrentsAvailable = new TorrentList(torrents.toArray(new Torrent[torrents.size()]));
                        infoPanel.removeAll();
                        infoPanel.add(new JTextArea("Found " + torrents.size() + " torrent(s)"));
                        torrentsAvailable.setTorrentSelectedListener(new TorrentSelectedListener() {
                            @Override
                            public void actionPerformed(TorrentSelectedEvent event) {
                                downloadTorrent(event.getTorrentSelected());
                            }
                        });
                        torrentPanel.removeAll();
                        torrentPanel.add(torrentsAvailable);
                        frame.pack();
                    }
                };
                new Thread(runnable).start();

                infoPanel.removeAll();
                infoPanel.add(new JTextArea("Searching torrents for " + event.getSerieSelected()));
                frame.pack();
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
