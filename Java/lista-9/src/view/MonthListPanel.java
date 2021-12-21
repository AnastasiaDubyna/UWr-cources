package view;

import model.Month;
import model.MyListCellRenderer;

import javax.swing.*;
import java.awt.*;

public class MonthListPanel extends JPanel {

    public MonthListPanel(Month currMonth) {
        Font font = new Font("Century Gothic", Font.PLAIN,22);

        setLayout(new GridBagLayout());
        setBackground(new Color(236, 254, 254));

        MonthList currMonthList = new MonthList(currMonth);
        MonthList previousMonthList = new MonthList(currMonth.getPreviousMonth());
        MonthList nextMonthList = new MonthList(currMonth.getNextMonth());

        JLabel currMonthLabel = new JLabel(currMonth.getMonthName());
        currMonthLabel.setFont(font);
        JLabel previousMonthLabel = new JLabel(currMonth.getPreviousMonth().getMonthName());
        previousMonthLabel.setFont(font);
        JLabel nextMonthLabel = new JLabel(currMonth.getNextMonth().getMonthName());
        nextMonthLabel.setFont(font);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(previousMonthLabel, c);
        c.gridy = 1;
        add(previousMonthList, c);
        c.gridx = 1;
        c.gridy = 0;
        add(currMonthLabel, c);
        c.gridy = 1;
        add(currMonthList, c);
        c.gridx = 2;
        c.gridy = 0;
        add(nextMonthLabel, c);
        c.gridy = 1;
        add(nextMonthList, c);
    }

    private class MonthList extends JList<String> {

        public MonthList(Month month) {
            super(month.generateListData().toArray(new String[0]));
            setFont(new Font("Century Gothic", Font.PLAIN,22));
            setBackground(new Color(236, 254, 254));
            setLayoutOrientation(JList.HORIZONTAL_WRAP);
            this.setCellRenderer(new MyListCellRenderer(month));

        }
    }
}
