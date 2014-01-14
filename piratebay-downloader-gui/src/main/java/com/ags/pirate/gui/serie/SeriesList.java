package com.ags.pirate.gui.serie;


import com.ags.pirate.common.model.Serie;
import com.ags.pirate.gui.event.SerieSelectedEvent;
import com.ags.pirate.gui.listener.DoubleClickListener;
import com.ags.pirate.gui.listener.SerieSelectedListener;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * @author Angel
 * @since 20/11/13
 */
public class SeriesList extends JList<Serie> {

    private SerieSelectedListener listener;

    public SeriesList() {
        this.addMouseListener(new DoubleClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Serie selectedValue = getSelectedValue();
                    listener.actionPerformed(new SerieSelectedEvent(SeriesList.this, selectedValue));
                }
            }
        });
    }

    @Override
    public void setListData(Serie[] listData) {
        super.setListData(listData);
    }

    public void setSerieSelectedListener(SerieSelectedListener listener) {
        this.listener = listener;
    }


}
