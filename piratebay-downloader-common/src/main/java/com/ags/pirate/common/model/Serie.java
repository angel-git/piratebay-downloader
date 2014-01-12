package com.ags.pirate.common.model;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Angel
 *         Date: 3/10/12
 *         Time: 21:05
 */
public class Serie {

    private String title;
    private String episode;
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Serie(String title, String episode, String className) {
        this.title = title;
        this.episode = episode;
        this.className = className;
    }

    public String getTitle() {
        return title;
    }

    public String getEpisode() {
        return episode;
    }

    @Override
    public String toString() {
        return title + " " + episode;
    }
}
