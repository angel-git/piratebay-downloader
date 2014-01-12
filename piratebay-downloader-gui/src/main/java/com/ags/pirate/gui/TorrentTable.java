package com.ags.pirate.gui;


import com.ags.pirate.common.model.Torrent;
import com.ags.pirate.gui.event.TorrentSelectedEvent;
import com.ags.pirate.gui.listener.DoubleClickListener;
import com.ags.pirate.gui.listener.TorrentSelectedListener;
import com.ags.pirate.gui.model.BeanItemTableModel;

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
