package controller;

import view.CalendarPane;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonsAction extends AbstractAction {
    CalendarPane calendarPane;
    int selectedTabIndex;
    String direction;

    public ButtonsAction(String name, CalendarPane calendarPane) {
        super(name);
        selectedTabIndex = calendarPane.getSelectedIndex();
        direction = name.equals("->") ? "forward" : "backwards";
        this.calendarPane = calendarPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (calendarPane.getSelectedIndex() == 0) {
            if (direction.equals("forward")) {
                calendarPane.showNextYear();
            } else {
                calendarPane.showPreviousYear();
            }
        } else {
            if (direction.equals("forward")) {
                calendarPane.showNextMonth();
            } else {
                calendarPane.showPreviousMonth();
            }
        }
        SwingUtilities.updateComponentTreeUI(calendarPane);

    }
}
