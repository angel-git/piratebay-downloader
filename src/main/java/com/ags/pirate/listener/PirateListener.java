package com.ags.pirate.listener;

import com.ags.pirate.event.PirateEvent;

/**
 * @author Angel
 * @since 20/11/13
 */
public interface PirateListener<E extends PirateEvent> {

    public void actionPerformed(E event);
}
