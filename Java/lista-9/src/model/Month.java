package model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Month {
    int year;
    int monthNumber;
    int monthLength;
    int firstDayOfMonth;
    int lastDayOfMonth;

    public Month(int year, int monthNumber) {
        this.year = year;
        this.monthNumber = monthNumber;
        monthLength = YearMonth.of(year, monthNumber).lengthOfMonth();

        firstDayOfMonth = new GregorianCalendar(year, monthNumber-1, 1).get(Calendar.DAY_OF_WEEK);
        firstDayOfMonth = firstDayOfMonth == 1 ? 7 : firstDayOfMonth - 1;

        lastDayOfMonth = new GregorianCalendar(year, monthNumber-1, monthLength).get(Calendar.DAY_OF_WEEK);
        lastDayOfMonth = lastDayOfMonth == 1 ? 7 : lastDayOfMonth - 1;

    }

    public ArrayList<String> generateMonth() {
        ArrayList<String> monthDays = new ArrayList<>();
        if (year == 1582 && monthNumber == 10) {
            for (int i = 1; i <= 4; i++) {
                monthDays.add(String.valueOf(i));
            }
            for (int i = 15; i <= 31; i++) {
                monthDays.add(String.valueOf(i));
            }
            return monthDays;
        }

        for (int i = 1; i < firstDayOfMonth; i++) {
            monthDays.add("");
        }
        for (int i = 1; i <= monthLength; i++) {
            monthDays.add(String.valueOf(i));
        }

        for (int i = 1; i <= 7 - lastDayOfMonth; i++) {
            monthDays.add("");
        }

        return monthDays;
    }

    public String getMonthName() {
        String[] monthNames = {
                                "January", "February", "March",
                                "April", "May", "June",
                                "July", "August", "September",
                                "October", "November", "December"
                                };
        return monthNames[monthNumber-1];
    }

    public int getRowsCount() {
        int temp = (firstDayOfMonth - 1 + monthLength) / 7;
        return (firstDayOfMonth - 1 + monthLength) % 7 == 0 ? temp : temp + 1;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public ArrayList<String> generateListData() {
        ArrayList<String> monthDays = this.generateMonth();
        String[] dayNames = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        int index = 0;
        ArrayList<String> data = new ArrayList<>(Arrays.asList(dayNames));
        data.addAll(monthDays);

        return data;
    }

    public Month getPreviousMonth() {
        return monthNumber == 1 ? new Month(year - 1, 12) : new Month(year, monthNumber - 1);
    }

    public Month getNextMonth() {
        return monthNumber == 12 ? new Month(year + 1, 1) : new Month(year, monthNumber + 1);
    }

}
