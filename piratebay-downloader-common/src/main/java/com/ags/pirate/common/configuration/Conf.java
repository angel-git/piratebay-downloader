package com.ags.pirate.common.configuration;

/**
 * @author Angel
 * @since 21/11/13
 */
public class Conf {
    private static Conf ourInstance = new Conf();

    public static Conf getInstance() {
        return ourInstance;
    }

    private Conf() {
    }
}
