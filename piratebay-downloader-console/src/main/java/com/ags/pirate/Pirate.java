package com.ags.pirate;

import com.ags.pirate.common.configuration.Configuration;
import com.ags.pirate.common.model.Serie;
import com.ags.pirate.common.model.Torrent;
import com.ags.pirate.service.CalendarService;
import com.ags.pirate.service.PirateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Angel
 *         Date: 3/10/12
 *         Time: 19:15
 */
public class Pirate  {

    private static Logger LOGGER = LoggerFactory.getLogger(Pirate.class);

    private final String lineSeparator = "\033[35m-------------------------";
    private final String back = "\033[36m";
    private final String search = "\033[32m";
    private final String lastep = "\033[31m";
    private final String firstep = "\033[33m";

    private PirateService pirateService;
    private CalendarService calendarService;


    public static void main(String... args) throws IOException {
        new Pirate().execute();
    }


    private void execute() {
        this.pirateService = new PirateService();
        this.calendarService = new CalendarService();
        List<Serie> series = calendarService.getSeries();
        if (series.size() < 1) {
            //no series found exit program
            LOGGER.warn("no series has been found for today");
            return;
        }
        try {
            search(series);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }


    private void search(List<Serie> series) throws IOException {

        LOGGER.info("press the number to search the torrents");
        LOGGER.info(back + "0: EXIT");
        LOGGER.info(search + "S: Search manually");
        LOGGER.info(lineSeparator);
        for (int i = 0; i < series.size(); i++) {
            Serie serie = series.get(i);
            String className = serie.getClassName();
            if (className.toLowerCase().equals("lastep")) {
                LOGGER.info(i + 1 + ": " + lastep + serie.getTitle() + " " + serie.getEpisode());
            } else if (className.toLowerCase().equals("firstep")) {
                LOGGER.info(i + 1 + ": " + firstep + serie.getTitle() + " " + serie.getEpisode());
            } else {
                LOGGER.info(i + 1 + ": " + serie.getTitle() + " " + serie.getEpisode());
            }
        }
        int option = readOption(series.size());
        Serie serie;
        if (option == 0) {
            return;
        } else if (option == 99) {
            serie = readSerie();
        } else {
            serie = series.get(option - 1);
        }
        LOGGER.info("serie selected: " + serie);
        List<Torrent> torrents = pirateService.getTorrents(serie);

        if (torrents == null || torrents.size() < 1) {
            LOGGER.warn("no torrents found :-(");
            search(series);
        } else {

            LOGGER.info("press the number to download the serie");
            LOGGER.info(back + "0: BACK");
            LOGGER.info(lineSeparator);

            //calculate the space
            int spacerSize = getLongestTorrent(torrents);

            LOGGER.info("\033[33mName" + createSpacer(spacerSize) + "\t\tSeeds\t\tLeechers");
            for (int i = 0; i < torrents.size(); i++) {
                Torrent torrent = torrents.get(i);
                int difference = spacerSize - torrent.getName().length();
                LOGGER.info(i + 1 + " " + torrent.getName() + createSpacer(difference) + "\t\t" + torrent.getSeeds() + "\t\t" + torrent.getLeechers());
                LOGGER.info("  \033[36m" + torrent.getDescription());
            }
            int optionTorrent = readOption(torrents.size());
            if (optionTorrent == 0) {
                search(series);
            } else {
                pirateService.downloadTorrent(torrents.get(optionTorrent - 1), Configuration.getInstance().getUtorrent());
                search(series);
            }
        }

    }

    private String createSpacer(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private int getLongestTorrent(List<Torrent> torrents) {
        int maxLength = 0;
        for (Torrent torrent : torrents) {
            int nameLength = torrent.getName().length();
            int descLength = torrent.getDescription().length();
            int length = nameLength > descLength ? nameLength : descLength;
            if (length > maxLength) maxLength = length;
        }
        return maxLength;

    }

    private int readOption(int max) throws IOException {
        LOGGER.info("your option:");
        BufferedReader leerEntrada = new BufferedReader(new InputStreamReader(System.in));
        String option = leerEntrada.readLine();
        try {
            int i = Integer.parseInt(option);
            if (i > max || i < 0) {
                LOGGER.error("option not recognized");
                return readOption(max);
            }

            return i;

        } catch (Exception e) {
            if (option.equalsIgnoreCase("s")) {
                return 99;
            } else {
                LOGGER.error("the option must be a number");
                return readOption(max);
            }
        }
    }

    private Serie readSerie() throws IOException {
        LOGGER.info("type the torrent's name:");
        BufferedReader leerEntrada = new BufferedReader(new InputStreamReader(System.in));
        String option = leerEntrada.readLine();
        return new Serie(option, "", "");
    }


}
