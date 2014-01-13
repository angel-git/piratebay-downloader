package com.ags.pirate.gui.torrent;


import com.ags.pirate.common.model.Torrent;
import com.ags.pirate.gui.event.TorrentSelectedEvent;
import com.ags.pirate.gui.listener.DoubleClickListener;
import com.ags.pirate.gui.listener.TorrentSelectedListener;

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
