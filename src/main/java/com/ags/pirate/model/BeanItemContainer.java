package com.ags.pirate.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Angel
 * @since 23/11/13
 */
public class BeanItemContainer<B> {

    private List<BeanItem<B>> collection;

    public BeanItemContainer(List<B> collection) {
        this.collection = new ArrayList<BeanItem<B>>(collection.size());
        for (B o : collection) {
            this.collection.add(new BeanItem(o));
        }
    }


    public int size() {
        return collection.size();
    }

    public BeanItem get(int rowIndex) {
        return collection.get(rowIndex);
    }
}
