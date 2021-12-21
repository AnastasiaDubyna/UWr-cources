package controller;

import view.CalendarPane;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ScrollerListener implements AdjustmentListener {
    CalendarPane calendarPane;

    public ScrollerListener(CalendarPane calendarPane) {
        this.calendarPane = calendarPane;
    }
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        int value = e.getValue();
        if (calendarPane.getSelectedIndex() == 0) {
            calendarPane.changeYear(value);
        } else if (value > 0 && value < 13){
            calendarPane.changeMonth(calendarPane.getCurrYear().getMonthByIndex(value));
        }
    }
}
