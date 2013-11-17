package com.ags.pirate.web;

import com.ags.pirate.model.Serie;
import com.ags.pirate.model.Torrent;
import com.ags.pirate.web.downloader.HTMLDownloader;
import com.ags.pirate.web.downloader.HTMLProxyDownloader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Angel
 *         Date: 3/10/12
 *         Time: 22:06
 */
public class PirateBayParser {

    private static Logger LOGGER = LoggerFactory.getLogger(PirateBayParser.class);

    private final String password;
    private final String user;
    private final String pirateHost;


    public PirateBayParser(String user, String password, String pirateHost) {
        this.user=user;
        this.password=password;
        this.pirateHost=pirateHost;
    }

    public List<Torrent> searchSerie(boolean useProxy,Serie serie)  {

        List<Torrent> torrents = new ArrayList<Torrent>();

         String html;
        try {
            if (useProxy) {
                html = new HTMLProxyDownloader(user,password).getHtml(pirateHost+"/search/"+convertSearchQuery(serie)+"/0/7/0");
            } else {
                html = new HTMLDownloader().getHtml(pirateHost+"/search/"+convertSearchQuery(serie)+"/0/7/0");
            }
        } catch (Exception e) {
            return torrents;
        }

        Document document = Jsoup.parse(html);
        Element searchResult = document.getElementById("searchResult");
        if (searchResult == null) {
            return torrents;
        }
        Elements results = searchResult.getElementsByTag("tr");
        LOGGER.info("torrents found: "+(results.size()-2));
        for (int i = 2; i < results.size(); i++) {
            torrents.add(parseTorrent(results.get(i)));
        }

        return torrents;


    }

    private static Torrent parseTorrent(Element element) {
        try {
            String name = element.getElementsByClass("detName").get(0).getElementsByClass("detLink").get(0).text();
            String link = element.getElementsByAttributeValueStarting("href", "magnet").get(0).getElementsByTag("a").attr("href");
            int seeds = Integer.parseInt(element.getElementsByTag("td").get(2).text());
            int leeds = Integer.parseInt(element.getElementsByTag("td").get(3).text());
            String desc = element.getElementsByTag("td").get(1).getElementsByClass("detDesc").text();
            return new Torrent(name,leeds,seeds,link,desc);
        } catch (RuntimeException e) {
            LOGGER.error("an error raised when parsing the table of the results!!");
            throw e;
        }


    }

    private static String convertSearchQuery(Serie serie) {
        String toString = serie.toString();
        return toString.replace(" ","%20");
    }
}
