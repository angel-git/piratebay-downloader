package com.ags.pirate.gui.mvc;

import javax.swing.*;
import java.awt.*;

/**
 * @author Angel
 * @since 12/01/14
 */
public abstract class AbstractView<C extends AbstractController, M extends AbstractModel> extends JPanel implements View<C,M> {

    private C controller;
    private M model;

    public AbstractView() {
        this.model = createModel();
        this.controller = createController(this.model);
    }

    public C getController() {
        return controller;
    }

    public M getModel() {
        return model;
    }

}
