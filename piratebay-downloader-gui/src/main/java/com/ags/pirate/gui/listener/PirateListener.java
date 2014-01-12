package com.ags.pirate.gui.listener;

import com.ags.pirate.gui.event.PirateEvent;

/**
 * @author Angel
 * @since 20/11/13
 */
public interface PirateListener<E extends PirateEvent> {

    public void actionPerformed(E event);
}
