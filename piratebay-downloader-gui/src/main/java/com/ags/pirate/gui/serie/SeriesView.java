package com.ags.pirate.gui.serie;

import com.ags.pirate.common.model.Serie;
import com.ags.pirate.gui.event.SerieSelectedEvent;
import com.ags.pirate.gui.fal.ColorProvider;
import com.ags.pirate.gui.listener.ClickListener;
import com.ags.pirate.gui.listener.SerieSelectedListener;
import com.ags.pirate.gui.mvc.AbstractView;
import net.miginfocom.swing.MigLayout;
import org.joda.time.LocalDate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * The panel that hold the list of series.
 * @author Angel
 * @since 12/01/14
 */
public class SeriesView extends AbstractView<SeriesController,SeriesModel> {

    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    private final JButton prevButton;
    private final JButton nextButton;
    private SeriesList seriesAvailable;
    private JPanel changeDatePanel;
    private JButton searchButton;
    private SerieSelectedListener listener;
    private LocalDate todayDate;
    private final JScrollPane scrollSeries;
    private final JLabel dateLabel;

    public SeriesView() {
        setLayout(new MigLayout("flowy","[]","[c,pref!][c,grow,100,fill][c,pref!]"));
        todayDate = new LocalDate();
        prevButton = new JButton(new ImageIcon(getClass().getResource("back.png")));
        nextButton = new JButton(new ImageIcon(getClass().getResource("forward.png")));
        changeDatePanel = new JPanel(new MigLayout("","[c,grow,50,fill][][c,grow,50,fill]","[]"));
        changeDatePanel.add(prevButton);
        dateLabel = new JLabel(todayDate.toString(DD_MM_YYYY));
        changeDatePanel.add(dateLabel);
        changeDatePanel.add(nextButton);
        seriesAvailable = new SeriesList();
        seriesAvailable.setCellRenderer(new SerieListCellRenderer());
        searchButton = new JButton("Custom search");
        scrollSeries = new JScrollPane(seriesAvailable);
        scrollSeries.setMinimumSize(new Dimension(210,0));
        add(changeDatePanel);
        add(scrollSeries);
        add(searchButton);
        initListeners();
        populateSeriesModel(todayDate);
    }



    private void populateSeriesModel(LocalDate date){
        getController().searchSeries(date);
        List<Serie> series = getModel().getSerieList();
        seriesAvailable.setListData(series.toArray(new Serie[series.size()]));
    }

    private void initListeners() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(getParent(), "Search torrents");
                if (input != null && !input.trim().equals("")) {
                    listener.actionPerformed(new SerieSelectedEvent(this,new Serie(input, "", "")));
                }
            }
        });
        prevButton.addMouseListener(new ClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                todayDate = todayDate.minusDays(1);
                dateLabel.setText(todayDate.toString(DD_MM_YYYY));
                populateSeriesModel(todayDate);
            }
        });
        nextButton.addMouseListener(new ClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                todayDate = todayDate.plusDays(1);
                dateLabel.setText(todayDate.toString(DD_MM_YYYY));
                populateSeriesModel(todayDate);
            }
        });
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
        setBackground(ColorProvider.getMainBackgroundColor());
        scrollSeries.setBackground(ColorProvider.getMainBackgroundColor());
        scrollSeries.getVerticalScrollBar().setUI(new com.ags.pirate.gui.fal.ScrollBar());
        scrollSeries.getHorizontalScrollBar().setUI(new com.ags.pirate.gui.fal.ScrollBar());
        seriesAvailable.setBackground(ColorProvider.getMainBackgroundColor());
        seriesAvailable.setForeground(ColorProvider.getMainFontColor());
        seriesAvailable.setSelectionBackground(ColorProvider.getMainSelectedColor());
        seriesAvailable.setSelectionForeground(ColorProvider.getMainFontColor());
        /** search button **/
        searchButton.setBackground(ColorProvider.getSecondaryBackgroundColor());
        searchButton.setForeground(ColorProvider.getSecondaryFontColor());
        searchButton.setFocusPainted(false);

        /** change date buttons **/
        dateLabel.setForeground(ColorProvider.getSecondaryFontColor());
        changeDatePanel.setBackground(ColorProvider.getMainBackgroundColor());
        prevButton.setBorderPainted(false);
        prevButton.setFocusPainted(false);
        prevButton.setContentAreaFilled(false);
        prevButton.setRolloverEnabled(true);
        prevButton.setRolloverIcon(new ImageIcon(getClass().getResource("backFocus.png")));
        nextButton.setBorderPainted(false);
        nextButton.setFocusPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setRolloverEnabled(true);
        nextButton.setRolloverIcon(new ImageIcon(getClass().getResource("forwardFocus.png")));

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
