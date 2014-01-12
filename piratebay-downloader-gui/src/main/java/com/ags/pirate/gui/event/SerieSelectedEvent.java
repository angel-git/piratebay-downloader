package com.ags.pirate.gui.event;


import com.ags.pirate.common.model.Serie;

/**
 * @author Angel
 * @since 20/11/13
 */
public class SerieSelectedEvent extends PirateEvent {


    private Serie serieSelected;

    public SerieSelectedEvent(Object source, Serie serieSelected) {
        super(source);
        this.serieSelected = serieSelected;
    }

    public Serie getSerieSelected() {
        return serieSelected;
    }
}
