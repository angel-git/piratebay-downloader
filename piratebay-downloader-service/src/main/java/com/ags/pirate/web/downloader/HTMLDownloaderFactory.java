package com.ags.pirate.web.downloader;

import com.ags.pirate.common.configuration.Configuration;

/**
 * @author Angel
 * @since 12/01/14
 */
public class HTMLDownloaderFactory {

    /**
     * creates the proper Downloader regarding the configuration.
     * @return the proper Downloader regarding the configuration.
     */
    public static Downloader createDownloader() {
        Configuration configuration = Configuration.getInstance();
        if (configuration.isProxyEnabled()) {
            return new HTMLProxyDownloader(
                    configuration.getProxyUser(),
                    configuration.getProxyPassword(),
                    configuration.getProxyHost(),
                    configuration.getProxyPort());
        } else {
            return new HTMLDownloader();
        }
    }
}
