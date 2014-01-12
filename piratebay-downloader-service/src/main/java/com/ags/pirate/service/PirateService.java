package com.ags.pirate.service;

import com.ags.pirate.common.model.Serie;
import com.ags.pirate.common.model.Torrent;
import com.ags.pirate.web.CalendarParser;
import com.ags.pirate.web.PirateBayParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author Angel
 * @since 20/11/13
 */
public class PirateService {

    private static Logger LOGGER = LoggerFactory.getLogger(PirateService.class);

    private PirateBayParser pirateBayParser;

    public PirateService() {
        this.pirateBayParser = new PirateBayParser();
    }

    public List<Torrent> getTorrents(Serie serie) {
        return pirateBayParser.searchSerie(serie);
    }

    public void downloadTorrent(Torrent torrent, String command) {
        LOGGER.info("downloading " + torrent.getName());
        try {
            LOGGER.info("executing uTorrent...");
            ProcessBuilder processBuilder = new ProcessBuilder(command, torrent.getLink());
            processBuilder.start();
            LOGGER.info("uTorrent started");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
