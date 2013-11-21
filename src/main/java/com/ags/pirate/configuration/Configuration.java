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


    private static Configuration ourInstance = new Configuration("configuration.properties");

    public static Configuration getInstance() {
        return ourInstance;
    }

    private Configuration(String configurationFile) {
        InputStream resourceAsStream = getClass().getResourceAsStream(configurationFile);
        this.prop = new Properties();
        try {
            prop.load(resourceAsStream);
            resourceAsStream.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private Properties prop = new Properties();

    private final static String PROXY_HOST = "pirate.http.proxyHost";
    private final static String PROXY_PORT = "pirate.http.proxyPort";
    private final static String PROXY_USER = "pirate.http.proxyUser";
    private final static String PROXY_PASSWORD = "pirate.http.proxyPassword";
    private final static String UTORRENT = "pirate.utorrent.folder";
    private final static String PIRATEBAY_HOST = "pirate.host";
    private final static String PROXY_ACTIVATED = "pirate.http.proxyActivated";


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

    public boolean isProxyEnabled(){
        return Boolean.parseBoolean(prop.getProperty(PROXY_ACTIVATED).toUpperCase());
    }
}
