package com.ags.pirate.gui.mvc;

import javax.swing.*;
import java.awt.*;

/**
 * @author Angel
 * @since 12/01/14
 */
public abstract class AbstractScrollView<C extends AbstractController, M extends AbstractModel> extends JScrollPane implements View<C,M>{

    private C controller;
    private M model;

    protected AbstractScrollView(Component view) {
        super(view);
    }

    protected AbstractScrollView() {
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
