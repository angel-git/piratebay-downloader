package com.ags.pirate.web;

import com.ags.pirate.common.model.Serie;
import com.ags.pirate.web.downloader.Downloader;
import com.ags.pirate.web.downloader.HTMLDownloaderFactory;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Angel
 *         Date: 3/10/12
 *         Time: 20:23
 */
public class CalendarParser  {

    public static final String CALENDAR_URL = "http://www.pogdesign.co.uk/cat";
    private static Logger LOGGER = LoggerFactory.getLogger(CalendarParser.class);
    private DateTimeFormatter dateFormat;
    private Downloader downloader;

    public CalendarParser() {
        this.dateFormat = DateTimeFormat.forPattern("dd_M_yyyy");
        this.downloader = HTMLDownloaderFactory.createDownloader();
    }


    /**
     * parses the series of the input date.
     * @param date input date.
     * @return the series of the input date.
     */
    public List<Serie> parseCalendar(LocalDate date) {
        List<Serie> series = new ArrayList<Serie>();

        String html;
        try {
            html = downloader.getHtml(CALENDAR_URL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return series;
        }
        Document document = Jsoup.parse(html);
        String todayId = "d_"+dateFormat.print(date);
        Element today = document.getElementById(todayId);
        if (today != null) {
            Elements divs = today.getElementsByTag("div");
            LOGGER.info("TV series found for today: {}", new Integer[]{divs.size()});
            for (Element div : divs) {
                Elements ps = div.getElementsByTag("p");
                String className = ps.get(0).className();
                Elements as = div.getElementsByTag("a");
                Element serieTitle = as.get(0);
                Element serieEpisode = as.get(1);
                String serieTitleString = serieTitle.text();
                String serieEpisodeString = parseEpisode(serieEpisode.text());
                LOGGER.trace(serieTitleString + " " + serieEpisodeString);
                series.add(new Serie(serieTitleString, serieEpisodeString,className));
            }
        }
        return series;

    }

    /**
     * parses the series of today.
     * @return the series of today.
     */
    public List<Serie> parseCalendar() {
        return parseCalendar(new LocalDate());
    }


    private String parseEpisode(String seasonEpisode) {
        StringBuilder sb = new StringBuilder();
        String[] split = seasonEpisode.split(" ");
        String season = split[1];
        String ep = split[4];
        if (season.length() < 2) {
            sb.append("s0").append(season);
        } else {
            sb.append("s").append(season);
        }
        if (ep.length() < 2) {
            sb.append("e0").append(ep);
        } else {
            sb.append("e").append(ep);
        }
        return sb.toString();
    }


}
