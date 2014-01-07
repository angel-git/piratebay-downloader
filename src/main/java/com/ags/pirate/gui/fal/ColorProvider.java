package com.ags.pirate.gui.fal;

import java.awt.*;

/**
 * @author Angel
 * @since 22/12/13
 */
public class ColorProvider {

    private static final Color MAIN_BACKGROUND_COLOR = new Color(60,63,65);
    private static final Color MAIN_FONT_COLOR = new Color(208, 208, 208);
    private static final Color MAIN_SELECTED_COLOR = new Color(75,110,175);

    private static final Color SECONDARY_FONT_COLOR = new Color(172, 172, 222);
    private static final Color SECONDARY_BACKGROUND_COLOR = new Color(43,43,43);

    private static final Color KEYWORD_COLOR = new Color(203,108,46);

    private static final Color SERIE_PREMIERE = new Color(98,151,85);
    private static final Color SERIE_FINALE = new Color(209,103,90);


    public static Color getMainSelectedColor() {
        return MAIN_SELECTED_COLOR;
    }

    public static Color getKeywordColor() {
        return KEYWORD_COLOR;
    }

    public static Color getMainFontColor() {
        return MAIN_FONT_COLOR;

    }

    public static Color getSecondaryFontColor() {
        return SECONDARY_FONT_COLOR;
    }

    public static Color getMainBackgroundColor() {
        return MAIN_BACKGROUND_COLOR;
    }

    public static Color getSecondaryBackgroundColor() {
        return SECONDARY_BACKGROUND_COLOR;
    }

    public static Color getSeriePremiere() {
        return SERIE_PREMIERE;
    }

    public static Color getSerieFinale() {
        return SERIE_FINALE;
    }
}
