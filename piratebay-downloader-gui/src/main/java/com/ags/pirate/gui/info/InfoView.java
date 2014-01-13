package com.ags.pirate.gui.info;

import com.ags.pirate.gui.fal.ColorProvider;
import com.ags.pirate.gui.mvc.AbstractView;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * Info panel.
 * @author Angel
 * @since 13/01/14
 */
public class InfoView extends AbstractView<InfoController, InfoModel> {


    private JLabel searchingText;

    public InfoView() {
        //info texts
        this.searchingText = new JLabel();
        this.add(searchingText);
    }

    /**
     * Updates the information text.
     * @param icon
     * @param text
     */
    public void updateInfoText(ImageIcon icon, String text) {
        searchingText.setText(text);
        searchingText.setIcon(icon);
    }

    @Override
    public InfoModel createModel() {
        return new InfoModel();
    }

    @Override
    public InfoController createController(InfoModel model) {
        return new InfoController(model);
    }

    @Override
    public void applyFeelAndLook() {
        this.setBackground(ColorProvider.getMainBackgroundColor());
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.searchingText.setBackground(ColorProvider.getMainBackgroundColor());
        this.searchingText.setForeground(ColorProvider.getMainFontColor());
    }
}
