package com.ags.pirate.gui.serie;

import com.ags.pirate.gui.mvc.AbstractController;
import com.ags.pirate.service.CalendarService;

/**
 * @author Angel
 * @since 12/01/14
 */
public class SeriesController extends AbstractController<SeriesModel> {


    private CalendarService calendarService;

    public SeriesController(SeriesModel model) {
        super(model);
        this.calendarService = new CalendarService();
    }

    /**
     * Search the series and populates them to the model.
     */
    public void searchSeries() {
        getModel().setSerieList(calendarService.getSeries());
    }


}
