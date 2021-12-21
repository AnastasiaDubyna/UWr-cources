package view;

import controller.ButtonsAction;
import controller.ScrollerListener;
import controller.SpinnerListener;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    CalendarPane calendarPane;

    public ToolBar(CalendarPane calendarPane) {
        super();
        this.calendarPane = calendarPane;
        addNavigationButtons();
        addSpinner();
        addScroller();
    }

    private void addNavigationButtons() {
        JButton moveBackwards = new JButton(new ButtonsAction("<-", calendarPane));
        JButton moveForward = new JButton(new ButtonsAction("->", calendarPane));
        this.add(moveBackwards);
        this.add(moveForward);
    }

    private void addSpinner() {
        SpinnerModel value = new SpinnerNumberModel(2021, 1, 2500, 1);
        JSpinner spinner = new JSpinner(value);
////        spinner.setPreferredSize(new Dimension(10, 50));
//        Component mySpinnerEditor = spinner.getEditor();
//        JFormattedTextField spinnerTextField = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
//        spinnerTextField.setColumns(1);
        spinner.addChangeListener(new SpinnerListener(calendarPane));
        this.add(spinner);
    }

    private void addScroller() {
        JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 2021, 40, 0, 2500);
        scrollBar.addAdjustmentListener(new ScrollerListener(calendarPane));
        this.add(scrollBar);
    }
}
