package com.ags.pirate.gui.event;

/**
 * @author Angel
 * @since 20/11/13
 */
public abstract class PirateEvent {

    private Object source;

    protected PirateEvent(Object source) {
        this.source = source;
    }
}
