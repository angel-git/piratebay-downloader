package com.ags.pirate.gui.serie;

import com.ags.pirate.common.model.Serie;
import com.ags.pirate.gui.event.SerieSelectedEvent;
import com.ags.pirate.gui.fal.ColorProvider;
import com.ags.pirate.gui.listener.SerieSelectedListener;
import com.ags.pirate.gui.mvc.AbstractView;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The panel that hold the list of series.
 * @author Angel
 * @since 12/01/14
 */
public class SeriesView extends AbstractView<SeriesController,SeriesModel> {

    private SeriesList seriesAvailable;
    private JButton searchButton;
    private SerieSelectedListener listener;

    public SeriesView() {
        this.setLayout(new MigLayout());
        this.init();
    }

    private void init(){
        getController().searchSeries();
        List<Serie> series = getModel().getSerieList();
        this.seriesAvailable = new SeriesList(series.toArray(new Serie[series.size()]));
        this.seriesAvailable.setCellRenderer(new SerieListCellRenderer());
        this.searchButton = new JButton("Custom search");
        this.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(getParent(), "Search torrents");
                if (input != null && !input.trim().equals("")) {
                    listener.actionPerformed(new SerieSelectedEvent(this,new Serie(input, "", "")));
                }
            }
        });
        this.add(seriesAvailable, "wrap");
        this.add(searchButton);
    }

    /**
     * adds the listener to perfom search and selected series event.
     * @param listener
     */
    public void setSerieSelectedListener(SerieSelectedListener listener) {
        this.listener = listener;
        seriesAvailable.setSerieSelectedListener(listener);
    }

    @Override
    public void applyFeelAndLook() {
        this.setBackground(ColorProvider.getMainBackgroundColor());
        seriesAvailable.setBackground(ColorProvider.getMainBackgroundColor());
        seriesAvailable.setForeground(ColorProvider.getMainFontColor());
        seriesAvailable.setSelectionBackground(ColorProvider.getMainSelectedColor());
        seriesAvailable.setSelectionForeground(ColorProvider.getMainFontColor());
        /** search button **/
        searchButton.setBackground(ColorProvider.getSecondaryBackgroundColor());
        searchButton.setForeground(ColorProvider.getSecondaryFontColor());
        searchButton.setFocusPainted(false);
    }

    @Override
    public SeriesModel createModel() {
        return new SeriesModel();
    }

    @Override
    public SeriesController createController(SeriesModel model) {
        return new SeriesController(model);
    }

    public void setEnabled(boolean enabled) {
        seriesAvailable.setEnabled(enabled);
    }
}
