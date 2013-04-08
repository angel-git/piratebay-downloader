package com.ags.pirate.web.downloader;

/**
 * @author AGS
 *         Date: 4/10/12
 */
public interface Downloader {

    /**
     * Returns the html associated into the url.
     * @param url
     * @return
     * @throws Exception
     */
    public String getHtml(String url) throws Exception;
}
