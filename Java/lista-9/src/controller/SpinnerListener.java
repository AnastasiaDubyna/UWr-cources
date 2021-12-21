package controller;

import view.CalendarPane;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerListener implements ChangeListener {
    CalendarPane calendarPane;

    public SpinnerListener(CalendarPane calendarPane) {
        this.calendarPane = calendarPane;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int value = (int) ((JSpinner)e.getSource()).getValue();
        if (calendarPane.getSelectedIndex() == 0) {
            calendarPane.changeYear(value);
        } else if (value > 0 && value < 13){
            calendarPane.changeMonth(calendarPane.getCurrYear().getMonthByIndex(value));
        }
    }
}
