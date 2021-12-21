package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    CalendarPane calendarPane;
    ToolBar toolBar;

    public MainFrame() {
        super("Calendar");
        calendarPane = new CalendarPane();
        toolBar = new ToolBar(calendarPane);

        this.add(calendarPane);

        Container contentPane = this.getContentPane();
        contentPane.add(toolBar, BorderLayout.SOUTH);

        this.setSize(1500, 800);
        this.setVisible(true);

    }
}
