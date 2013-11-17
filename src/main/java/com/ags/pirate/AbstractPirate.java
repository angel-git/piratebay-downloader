package com.ags.pirate;

import com.ags.pirate.configuration.Configuration;
import com.ags.pirate.model.Serie;
import com.ags.pirate.model.Torrent;
import com.ags.pirate.web.CalendarParser;
import com.ags.pirate.web.PirateBayParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author Angel
 * @since 17/11/13
 */
public class AbstractPirate {

    protected static Logger LOGGER = LoggerFactory.getLogger(Pirate.class);
    protected Configuration configuration;

    public AbstractPirate() {
       loadConfiguration("configuration.properties");
    }

    protected void loadConfiguration(String configurationFile)  {
        try {
            this.configuration = new Configuration(configurationFile);
        } catch (IOException e) {
            LOGGER.error("There are some problems with the configuration file. Please review it");
            System.exit(0);
        }
    }

    protected List<Serie> getSeries(boolean useProxy) {
        return new CalendarParser(this.configuration.getProxyUser(), this.configuration.getProxyPassword()).parseCalendar(useProxy);
    }

    protected List<Torrent> getTorrents(boolean useProxy, Serie serie) {
       return new PirateBayParser(this.configuration.getProxyUser(),
                this.configuration.getProxyPassword(),
                this.configuration.getPiratebayHost()).searchSerie(useProxy, serie);
    }


    protected void downloadTorrent(Torrent torrent) {
        LOGGER.info("downloading " + torrent.getName());
        try {
            String command = this.configuration.getUtorrent();
            LOGGER.info("executing uTorrent...");
            ProcessBuilder processBuilder = new ProcessBuilder(command, torrent.getLink());
            processBuilder.start();
            LOGGER.info("uTorrent started");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
