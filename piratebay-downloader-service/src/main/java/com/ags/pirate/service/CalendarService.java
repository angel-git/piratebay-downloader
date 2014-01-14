package com.ags.pirate.service;

import com.ags.pirate.common.model.Serie;
import com.ags.pirate.web.CalendarParser;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Angel
 * @since 12/01/14
 */
public class CalendarService {

    private static Logger LOGGER = LoggerFactory.getLogger(CalendarService.class);

    private CalendarParser calendarParser;

    public CalendarService() {
        this.calendarParser = new CalendarParser();
    }

    public List<Serie> getSeries() {
        return calendarParser.parseCalendar();
    }

    public List<Serie> getSeries(LocalDate date) {
        return calendarParser.parseCalendar(date);
    }


}
