package com.ags.pirate.gui;

import com.ags.pirate.event.TorrentSelectedEvent;
import com.ags.pirate.listener.DoubleClickListener;
import com.ags.pirate.listener.TorrentSelectedListener;
import com.ags.pirate.model.BeanItem;
import com.ags.pirate.model.BeanItemContainer;
import com.ags.pirate.model.BeanItemTableModel;
import com.ags.pirate.model.Torrent;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * @author Angel
 * @since 23/11/13
 */
public class TorrentTable extends JTable {

    private TorrentSelectedListener listener;

    public TorrentTable() {
        this.addMouseListener(new DoubleClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (getModel() instanceof BeanItemTableModel) {
                        Object torrent = ((BeanItemTableModel) getModel()).getRow(getSelectedRow());
                        listener.actionPerformed(new TorrentSelectedEvent(TorrentTable.this, (Torrent) torrent));
                    }
                }
            }
        });
    }

    public void setTorrentSelectedListener(TorrentSelectedListener listener) {
        this.listener = listener;
    }


}
