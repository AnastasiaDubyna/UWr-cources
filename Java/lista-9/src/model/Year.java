package model;

import java.util.ArrayList;

public class Year {
    int yearNumber;
    ArrayList<Month> months;

    public Year(int yearNumber) {
        this.yearNumber = yearNumber;
        months = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            months.add(new Month(yearNumber, i));
        }
    }

    public ArrayList<Month> getMonths() {
        return months;
    }

    public Month getMonthByIndex(int index) {
        return months.get(index - 1);
    }

    public int getYearNumber() {
        return yearNumber;
    }

    public Year getPreviousYear() {
        return new Year(yearNumber - 1);
    }

    public Year getNextYear() {
        return new Year(yearNumber + 1);
    }

    @Override
    public String toString() {
        return String.valueOf(yearNumber);
    }
}
