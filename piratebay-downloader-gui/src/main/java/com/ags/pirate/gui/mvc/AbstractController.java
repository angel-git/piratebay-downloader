package com.ags.pirate.gui.mvc;

/**
 * @author Angel
 * @since 12/01/14
 */
public abstract class AbstractController<M extends AbstractModel> implements Controller {

    private M model;

    protected AbstractController(M model) {
        this.model = model;
    }

    public M getModel() {
        return model;
    }
}