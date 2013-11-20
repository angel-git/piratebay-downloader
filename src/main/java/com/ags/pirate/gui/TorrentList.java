package com.ags.pirate.gui;

import com.ags.pirate.event.TorrentSelectedEvent;
import com.ags.pirate.listener.DoubleClickListener;
import com.ags.pirate.listener.TorrentSelectedListener;
import com.ags.pirate.model.Torrent;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * @author Angel
 * @since 20/11/13
 */
public class TorrentList extends JList<Torrent> {

    private TorrentSelectedListener listener;

    public TorrentList(Torrent[] torrents) {
        super(torrents);
        this.addMouseListener(new DoubleClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Torrent torrentSelected = getSelectedValue();
                    listener.actionPerformed(new TorrentSelectedEvent(TorrentList.this, torrentSelected));
                }
            }
        });
    }

    public void setTorrentSelectedListener(TorrentSelectedListener listener) {
        this.listener = listener;
    }


}
