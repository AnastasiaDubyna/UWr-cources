package view;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    DrawingPanel drawingPanel;

    public MainFrame() {
        super();

        String savedFilePath = "./saved.txt";

        drawingPanel = new DrawingPanel();
        this.add(drawingPanel);
        this.setSize(1920, 1080);


        drawingPanel.graph.deserialize(savedFilePath);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                drawingPanel.graph.serialize(savedFilePath);
                System.exit(0);
            }
        });

        this.setVisible(true);
    }
}
