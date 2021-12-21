package view;

import controller.TabListener;
import model.Month;
import model.Year;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarPane extends JTabbedPane {
    Year currYear;
    Month currMonth;
    private YearPanel yearPanel;
    private MonthPanel monthPanel;

    public CalendarPane() {
        super();
        currYear = new Year(new GregorianCalendar().get(Calendar.YEAR));
        currMonth = currYear.getMonthByIndex(new GregorianCalendar().get(Calendar.MONTH) + 1);

        addYearTab();
        addMonthTab();

        this.addChangeListener(new TabListener());

    }

    private void addYearTab() {
        yearPanel = new YearPanel(currYear, this);
        this.addTab(currYear.toString(), yearPanel);
        this.setComponentAt(0, yearPanel);
    }
//
    private void addMonthTab() {
        MonthListPanel monthListPanel = new MonthListPanel(currMonth);

        this.addTab(currMonth.getMonthName(), monthListPanel);
        this.setComponentAt(1, monthListPanel);
    }

    public void showNextYear() {
        this.remove(yearPanel);
        currYear = currYear.getNextYear();
        currMonth = currYear.getMonthByIndex(1);
        addYearTab();
        addMonthTab();
    }

    public void showPreviousYear() {
        this.remove(yearPanel);
        currYear = currYear.getPreviousYear();
        currMonth = currYear.getMonthByIndex(12);
        addYearTab();
        addMonthTab();
    }

    public void showNextMonth() {
        this.remove(monthPanel);
        if (currMonth.getMonthNumber() == 12) {
            showNextYear();
            this.setSelectedIndex(1);
        } else {
            currMonth = currMonth.getNextMonth();
            addMonthTab();
        }
    }

    public void showPreviousMonth() {
        this.remove(monthPanel);
        if (currMonth.getMonthNumber() == 1) {
            showPreviousYear();
            this.setSelectedIndex(1);
        } else {
            currMonth = currMonth.getPreviousMonth();
            addMonthTab();
        }
    }

    public void changeMonth(Month month) {
        this.remove(monthPanel);
        currMonth = month;
        addMonthTab();
    }

    public void changeYear(int yearNumber) {
        this.remove(yearPanel);
        currYear = new Year(yearNumber);
        currMonth = currYear.getMonthByIndex(1);
        addYearTab();
        addMonthTab();
    }

    public Year getCurrYear() {
        return currYear;
    }
}
