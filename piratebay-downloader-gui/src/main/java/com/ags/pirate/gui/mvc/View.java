package com.ags.pirate.gui.mvc;

/**
 * @author Angel
 * @since 12/01/14
 */
public interface View<C,M> {

    public M createModel();

    public C createController(M model);

    public void applyFeelAndLook();
}
