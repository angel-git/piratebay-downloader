package com.ags.pirate.event;

import com.ags.pirate.model.Torrent;

/**
 * @author Angel
 * @since 20/11/13
 */
public class TorrentSelectedEvent extends PirateEvent {


    private Torrent torrentSelected;

    public TorrentSelectedEvent(Object source, Torrent torrentSelected) {
        super(source);
        this.torrentSelected = torrentSelected;
    }


    public Torrent getTorrentSelected() {
        return torrentSelected;
    }
}
