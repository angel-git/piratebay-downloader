package com.ags.pirate.gui.torrent;

import com.ags.pirate.common.model.Torrent;
import com.ags.pirate.gui.model.BeanItemContainer;
import com.ags.pirate.gui.model.BeanItemTableModel;
import com.ags.pirate.gui.mvc.AbstractModel;

import java.util.List;

/**
 * @author Angel
 * @since 12/01/14
 */
public class TorrentModel extends AbstractModel {


    private BeanItemTableModel torrentBeanItemTableModel;

    public BeanItemTableModel getBeanItemTable() {
        return torrentBeanItemTableModel;
    }

    public void populateModel(List<Torrent> torrents) {
        torrentBeanItemTableModel = createTorrentBeanItemTableModel(torrents);
    }

    private BeanItemTableModel createTorrentBeanItemTableModel(java.util.List<Torrent> torrents) {
        BeanItemContainer<Torrent> beanItemContainer = new BeanItemContainer<Torrent>(torrents);
        BeanItemTableModel tableModel = new BeanItemTableModel(beanItemContainer);
        tableModel.setVisibleColumns(new String[]{"info", "seeds", "leechers"});
        tableModel.setColumnHeader("info", "Torrent's name");
        return tableModel;
    }
}
