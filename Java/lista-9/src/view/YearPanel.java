package view;

import controller.HighlightListener;
import model.Month;
import model.Year;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class YearPanel extends JPanel {

    public YearPanel(Year year, CalendarPane calendarPane) {
        super();
        this.setLayout(new GridLayout(3, 4));
        ArrayList<Month> months = year.getMonths();
        for (Month month : months) {
            MonthPanel monthPanel = new MonthPanel(month);
            monthPanel.addMouseListener(new HighlightListener(calendarPane));
            this.add(monthPanel);
        }
    }

}