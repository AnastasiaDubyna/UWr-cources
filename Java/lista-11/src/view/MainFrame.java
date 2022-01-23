package view;

import javax.swing.*;

public class MainFrame extends JFrame {
    DrawingPanel drawingPanel;

    public MainFrame() {
        super();

        drawingPanel = new DrawingPanel();
        this.add(drawingPanel);
        this.setSize(1920, 1080);
        this.setVisible(true);
    }
}
