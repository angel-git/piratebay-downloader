package com.ags.pirate.gui.fal;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * @author Angel
 * @since 4/01/14
 */
public class ScrollBar extends BasicScrollBarUI {



    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createEmptyButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createEmptyButton();
    }

    protected void configureScrollBarColors()
    {
        LookAndFeel.installColors(scrollbar, "ScrollBar.background", "ScrollBar.foreground");
        /** scroller border color left and top **/
        thumbHighlightColor = ColorProvider.getMainBackgroundColor();
        /** scroller border color right and bottom **/
        thumbLightShadowColor = ColorProvider.getMainBackgroundColor();
        /** scroller border shadow color right and bottom **/
        thumbDarkShadowColor = new Color(0,0,0,0);
        /** scroller color **/
        thumbColor = ColorProvider.getMainSelectedColor();
        /** background color **/
        trackColor = ColorProvider.getMainBackgroundColor();
        /** ?? **/
        trackHighlightColor = Color.RED;
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
    {
        if(thumbBounds.isEmpty() || !scrollbar.isEnabled())     {
            return;
        }

        int w = thumbBounds.width;
        int h = thumbBounds.height;

        g.translate(thumbBounds.x, thumbBounds.y);

        g.setColor(thumbDarkShadowColor);
        g.drawRect(0, 0, w-1, h-1);
        g.setColor(thumbColor);
        g.fillRect(1, 1, w-2, h-2);

        g.setColor(thumbHighlightColor);
        g.drawLine(1, 1, 1, h-2);
        g.drawLine(2, 1, w-3, 1);

        g.setColor(thumbLightShadowColor);
        g.drawLine(2, h-2, w-2, h-2);
        g.drawLine(w-2, 1, w-2, h-3);

        g.translate(-thumbBounds.x, -thumbBounds.y);
    }


    private JButton createEmptyButton() {
        JButton jbutton = new JButton();
        jbutton.setPreferredSize(new Dimension(0, 0));
        jbutton.setMinimumSize(new Dimension(0, 0));
        jbutton.setMaximumSize(new Dimension(0, 0));
        return jbutton;
    }
}
