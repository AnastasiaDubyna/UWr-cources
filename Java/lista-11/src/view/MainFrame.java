package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    DrawingPanel drawingPanel;

    public MainFrame() {
        super();
        drawingPanel = new DrawingPanel();
        JScrollPane scrollablePanel = new JScrollPane(drawingPanel);
        scrollablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        drawingPanel.setPreferredSize(new Dimension(1920, 1080));
        this.add(scrollablePanel);
        this.setSize(1000, 800);
        this.setVisible(true);
    }
}
