package com.ags.pirate.gui.mvc;

/**
 * @author Angel
 * @since 12/01/14
 */
public interface Controller<M extends Model> {

    public M getModel();

}
