package com.ags.pirate.gui;

import com.ags.pirate.gui.fal.ColorProvider;
import com.ags.pirate.model.Serie;

import javax.swing.*;
import java.awt.*;

/**
 * @author Angel
 * @since 7/01/14
 */
public class ListCellRenderer extends DefaultListCellRenderer  {


    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component listCellRendererComponent = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        setForeground(ColorProvider.getMainFontColor());
        Serie serie = (Serie) value;
        if (isSelected && cellHasFocus || isSelected) {
            setBackground(ColorProvider.getMainSelectedColor());
            setForeground(ColorProvider.getMainFontColor());
        } else if (serie.getClassName().toLowerCase().equals("lastep")) {
            setForeground(ColorProvider.getSerieFinale());
        } else if (serie.getClassName().toLowerCase().equals("firstep")) {
            setForeground(ColorProvider.getSeriePremiere());
        } else if (cellHasFocus) {
            return listCellRendererComponent;
        }
        return listCellRendererComponent;
    }

}
