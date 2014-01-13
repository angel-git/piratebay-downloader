package com.ags.pirate.gui.torrent;

import com.ags.pirate.common.model.Serie;
import com.ags.pirate.common.model.Torrent;
import com.ags.pirate.gui.mvc.AbstractController;
import com.ags.pirate.service.PirateService;

import java.util.List;

/**
 * @author Angel
 * @since 12/01/14
 */
public class TorrentController extends AbstractController<TorrentModel> {

    private PirateService pirateService;

    public TorrentController(TorrentModel model) {
        super(model);
        this.pirateService = new PirateService();
    }

    public void searchTorrents(Serie serie) {
        List<Torrent> torrents = pirateService.getTorrents(serie);
        getModel().populateModel(torrents);
    }

    public void downloadTorrent(Torrent torrentSelected, String utorrent) {
        pirateService.downloadTorrent(torrentSelected, utorrent);
    }
}
