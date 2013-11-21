package com.ags.pirate.web;

import com.ags.pirate.configuration.Configuration;
import com.ags.pirate.model.Serie;
import com.ags.pirate.web.downloader.HTMLDownloader;
import com.ags.pirate.web.downloader.HTMLProxyDownloader;
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

    private static Logger LOGGER = LoggerFactory.getLogger(CalendarParser.class);
    private final Configuration configuration;

    public CalendarParser() {
        this.configuration = Configuration.getInstance();
    }

    public List<Serie> parseCalendar() {
        List<Serie> series = new ArrayList<Serie>();

        String html;
        try {
            if (configuration.isProxyEnabled()) {
                html = new HTMLProxyDownloader(
                            configuration.getProxyUser(),
                            configuration.getProxyPassword(),
                            configuration.getProxyHost(),
                            configuration.getProxyPort())
                        .getHtml("http://www.pogdesign.co.uk/cat");
            } else {
                html = new HTMLDownloader().getHtml("http://www.pogdesign.co.uk/cat");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return series;
        }
        Document document = Jsoup.parse(html);


        Elements today = document.getElementsByClass("today");
        if (today.size() > 0) {
            Elements divs = today.get(0).getElementsByTag("div");
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
