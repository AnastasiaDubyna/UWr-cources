package controller;

import view.CalendarPane;
import view.MonthPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HighlightListener implements MouseListener {
    CalendarPane calendarPane;

    public HighlightListener(CalendarPane calendarPane) {
        this.calendarPane = calendarPane;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MonthPanel panel = (MonthPanel) e.getSource();
        calendarPane.changeMonth(panel.getMonth());
        calendarPane.setSelectedIndex(1);
        System.out.println("IN LISTENER: " + calendarPane.getSelectedIndex());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MonthPanel panel = (MonthPanel) e.getSource();
        panel.setBackground(new Color(201, 246, 246));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MonthPanel panel = (MonthPanel) e.getSource();
        panel.setBackground(new Color(236, 254, 254));
    }
}
