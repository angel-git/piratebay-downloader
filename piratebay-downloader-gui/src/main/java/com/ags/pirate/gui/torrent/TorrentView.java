package com.ags.pirate.gui.torrent;

import com.ags.pirate.common.configuration.Configuration;
import com.ags.pirate.common.model.Serie;
import com.ags.pirate.common.model.Torrent;
import com.ags.pirate.gui.event.TorrentFoundEvent;
import com.ags.pirate.gui.event.TorrentSelectedEvent;
import com.ags.pirate.gui.fal.ColorProvider;
import com.ags.pirate.gui.listener.TorrentFoundListener;
import com.ags.pirate.gui.listener.TorrentSelectedListener;
import com.ags.pirate.gui.model.BeanItemContainer;
import com.ags.pirate.gui.model.BeanItemTableModel;
import com.ags.pirate.gui.mvc.AbstractScrollView;

import javax.swing.*;
import java.util.List;

/**
 * @author Angel
 * @since 12/01/14
 */
public class TorrentView extends AbstractScrollView<TorrentController, TorrentModel> {


    private TorrentTable torrentTable;
    private TorrentFoundListener listener;

    public static final int MAX_WIDTH_SEEDS = 50;
    public static final int MAX_WIDTH_LEECHERS = 60;
    public static final int ROW_HEIGHT = 33;


    public TorrentView() {
        this.torrentTable = new TorrentTable();
        this.setViewportView(this.torrentTable);
        this.torrentTable.setAutoCreateRowSorter(true);
        this.init();
    }

    private void init() {
        torrentTable.setTorrentSelectedListener(new TorrentSelectedListener() {
            @Override
            public void actionPerformed(TorrentSelectedEvent event) {
                getController().downloadTorrent(event.getTorrentSelected(), Configuration.getInstance().getUtorrent());
            }
        });
    }

    /**
     * fills the list with the torrents found
     * @param serie the serie to look for
     */
    public void searchTorrent(Serie serie) {
        getController().searchTorrents(serie);
        BeanItemTableModel tableModel = getModel().getBeanItemTable();
        torrentTable.setModel(tableModel);
        torrentTable.getColumn("seeds").setMaxWidth(MAX_WIDTH_SEEDS);
        torrentTable.getColumn("leechers").setMaxWidth(MAX_WIDTH_LEECHERS);
        torrentTable.setRowHeight(ROW_HEIGHT);
        if (listener != null) {
            listener.actionPerformed(new TorrentFoundEvent(this,tableModel.getRowCount()));
        }
    }

    public void setTorrentsFoundListener(TorrentFoundListener listener) {
        this.listener = listener;
    }



    @Override
    public void applyFeelAndLook() {
        torrentTable.setBackground(ColorProvider.getSecondaryBackgroundColor());
        torrentTable.setForeground(ColorProvider.getSecondaryFontColor());
        torrentTable.getTableHeader().setBackground(ColorProvider.getMainBackgroundColor());
        torrentTable.getTableHeader().setForeground(ColorProvider.getMainFontColor());
        this.setBackground(ColorProvider.getMainBackgroundColor());
        this.getVerticalScrollBar().setUI(new com.ags.pirate.gui.fal.ScrollBar());
        this.getViewport().setBackground(ColorProvider.getSecondaryBackgroundColor());
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public TorrentModel createModel() {
        return new TorrentModel();
    }

    @Override
    public TorrentController createController(TorrentModel model) {
        return new TorrentController(model);
    }
}
