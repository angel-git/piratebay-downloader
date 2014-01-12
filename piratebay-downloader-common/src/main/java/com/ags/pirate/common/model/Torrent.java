package com.ags.pirate.common.model;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Angel
 *         Date: 3/10/12
 *         Time: 22:07
 */
public class Torrent {

    private String name;
    private int leechers;
    private int seeds;
    private String link;
    private String description;

    public Torrent(String name, int leechers, int seeds, String link,String description) {
        this.name = name;
        this.leechers = leechers;
        this.seeds = seeds;
        this.link = link;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getLeechers() {
        return leechers;
    }

    public int getSeeds() {
        return seeds;
    }

    public String getLink() {
        return link;
    }

    public String getDescription(){
        return description;
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        return sb.append("<html><b>").append(getName()).append("</b><br>").append(getDescription()).append("</html>").toString();
    }

    @Override
    public String toString() {
        return getName()+" "+ getSeeds() + " " + getLeechers();
    }
}
