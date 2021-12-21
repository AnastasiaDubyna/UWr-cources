package view;

import model.Month;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class MonthPanel extends JPanel {
    Month month;

    public MonthPanel(Month month) {
        super();
        this.month = month;
        this.setBackground(new Color(236, 254, 254));

    }

    public Month getMonth() {
        return month;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ArrayList<String> daysArray = month.generateMonth();
        String[] names = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        g.setFont(new Font("Century Gothic", Font.PLAIN,18));
        g.drawString(month.getMonthName().toUpperCase(Locale.ROOT), 130, 20);

        int x = 50;
        int y = 50;
        for (String name : names) {
            if (name.equals("SU")){
                g.setColor(Color.red);
            }
            g.drawString(name, x, y);
            x += 40;
        }
        g.setColor(Color.black);
        x = 50;
        y = 80;
        int index = 1;
        for (String day : daysArray) {
            if (!day.equals("")) {
                g.drawString(day, x, y);
            }
            x += 40;
            if (index % 7 == 0) {
                x = 50;
                y += 30;
            }
            index += 1;
        }

    }
}
