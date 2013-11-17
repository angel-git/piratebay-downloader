package com.ags.pirate.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class that holds the configuration of the application.
 * @author Angel
 * @since 17/11/13
 */
public class Configuration {


    private Properties prop = new Properties();

    private final static String PROXY_HOST = "pirate.http.proxyHost";
    private final static String PROXY_PORT = "pirate.http.proxyPort";
    private final static String PROXY_USER = "pirate.http.proxyUser";
    private final static String PROXY_PASSWORD = "pirate.http.proxyPassword";
    private final static String UTORRENT = "pirate.utorrent.folder";
    private final static String PIRATEBAY_HOST = "pirate.host";

    public Configuration(String configurationFile) throws IOException {
        InputStream resourceAsStream = getClass().getResourceAsStream(configurationFile);
        this.prop = new Properties();
        prop.load(resourceAsStream);
        resourceAsStream.close();
    }

    public String getProxyHost() {
        return prop.getProperty(PROXY_HOST);
    }

    public String getProxyPort() {
        return prop.getProperty(PROXY_PORT);
    }

    public String getProxyUser() {
        return prop.getProperty(PROXY_USER);
    }

    public String getProxyPassword() {
        return prop.getProperty(PROXY_PASSWORD);
    }

    public String getUtorrent() {
        return prop.getProperty(UTORRENT);
    }

    public String getPiratebayHost() {
        return prop.getProperty(PIRATEBAY_HOST);
    }
}
