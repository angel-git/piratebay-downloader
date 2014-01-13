package com.ags.pirate.gui.event;

import com.ags.pirate.common.model.Torrent;

import java.util.List;

/**
 * @author Angel
 * @since 12/01/14
 */
public class TorrentFoundEvent extends PirateEvent {

    private int size;

    public TorrentFoundEvent(Object source, int size) {
        super(source);
        this.size = size;
    }

    public int getCount() {
        return size;
    }
}
