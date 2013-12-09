package com.ags.pirate;

import com.ags.pirate.configuration.Configuration;
import com.ags.pirate.event.SerieSelectedEvent;
import com.ags.pirate.event.TorrentSelectedEvent;
import com.ags.pirate.gui.SeriesList;
import com.ags.pirate.gui.TorrentTable;
import com.ags.pirate.listener.SerieSelectedListener;
import com.ags.pirate.listener.TorrentSelectedListener;
import com.ags.pirate.model.BeanItemContainer;
import com.ags.pirate.model.BeanItemTableModel;
import com.ags.pirate.model.Serie;
import com.ags.pirate.model.Torrent;
import com.ags.pirate.service.PirateService;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Application with Swing as GUI.
 *
 * @author Angel
 * @since 17/11/13
 */
public class PirateGui {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private static Logger LOGGER = LoggerFactory.getLogger(Pirate.class);

    private PirateService pirateService;
    private JFrame frame;
    private SeriesList seriesAvailable;
    private JPanel infoPanel;
    private TorrentTable torrentTable;
    private JButton searchButton;

    private void execute() {
        this.pirateService = new PirateService();
        this.createComponents();
        this.createListeners();
        this.displayComponents();
    }


    private void createComponents() {
        //Create and set up the window.
        frame = new JFrame("PirateBay downloader");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MigLayout layout = new MigLayout("", "[][]", "[]");
        frame.getContentPane().setLayout(layout);

        //info panel
        infoPanel = new JPanel();
        frame.add(infoPanel, BorderLayout.SOUTH);

        //series list
        List<Serie> series = pirateService.getSeries();
        seriesAvailable = new SeriesList(series.toArray(new Serie[series.size()]));
        searchButton = new JButton("Custom search");
        JPanel seriesPanel = new JPanel();
        seriesPanel.setLayout(new MigLayout());
        frame.add(seriesPanel, BorderLayout.WEST);
        seriesPanel.add(seriesAvailable, "wrap");
        seriesPanel.add(searchButton);

        //torrent list
        torrentTable = new TorrentTable();
        torrentTable.setAutoCreateRowSorter(true);
        JScrollPane torrentPanel = new JScrollPane(torrentTable);
        frame.add(torrentPanel, BorderLayout.EAST);

    }

    private void createListeners() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Search torrents");
                if (input != null && !input.trim().equals("")) {
                    searchTorrents(new Serie(input, "", ""));
                }
            }
        });

        seriesAvailable.setSerieSelectedListener(new SerieSelectedListener() {
            @Override
            public void actionPerformed(final SerieSelectedEvent event) {
                searchTorrents(event.getSerieSelected());
            }
        });
    }


    private void displayComponents() {
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private BeanItemTableModel createTorrentBeanItemTableModel(List<Torrent> torrents) {
        BeanItemContainer<Torrent> beanItemContainer = new BeanItemContainer<Torrent>(torrents);
        BeanItemTableModel tableModel = new BeanItemTableModel(beanItemContainer);
        tableModel.setVisibleColumns(new String[]{"name", "seeds", "leechers"});
        tableModel.setColumnHeader("name", "Torrent's name");
        return tableModel;
    }

    private void searchTorrents(final Serie serie) {
        //different thread to update the UI
        seriesAvailable.setEnabled(false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    List<Torrent> torrents = pirateService.getTorrents(serie);
                    if (torrents.size() == 0) {
                        //TODO add alert message
                        LOGGER.warn("no torrents found!");
                    }
                    populateTorrents(torrents);
                } finally {
                    frame.pack();
                    seriesAvailable.setEnabled(true);
                }
            }
        };
        new Thread(runnable).start();

        infoPanel.removeAll();
        infoPanel.add(new JLabel(new ImageIcon(getClass().getResource("1-1.gif"))));
        infoPanel.add(new JTextArea("Searching torrents for " + serie.getTitle()));
        frame.pack();
    }

    private void populateTorrents(List<Torrent> torrents) {
        infoPanel.removeAll();
        infoPanel.add(new JTextArea("Found " + torrents.size() + " torrent(s)"));
        torrentTable.setTorrentSelectedListener(new TorrentSelectedListener() {
            @Override
            public void actionPerformed(TorrentSelectedEvent event) {
                pirateService.downloadTorrent(event.getTorrentSelected(), Configuration.getInstance().getUtorrent());
            }
        });
        BeanItemTableModel tableModel = createTorrentBeanItemTableModel(torrents);
        torrentTable.setModel(tableModel);
        torrentTable.getColumn("seeds").setMaxWidth(50);
        torrentTable.getColumn("leechers").setMaxWidth(60);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PirateGui().execute();
            }
        });
    }
}
