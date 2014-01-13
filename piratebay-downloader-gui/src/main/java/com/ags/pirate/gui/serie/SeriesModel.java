package com.ags.pirate.gui.serie;

import com.ags.pirate.common.model.Serie;
import com.ags.pirate.gui.mvc.AbstractModel;

import java.util.List;

/**
 * @author Angel
 * @since 12/01/14
 */
public class SeriesModel extends AbstractModel {

    private List<Serie> serieList;

    public List<Serie> getSerieList() {
        return serieList;
    }

    public void setSerieList(List<Serie> serieList) {
        this.serieList = serieList;
    }
}
