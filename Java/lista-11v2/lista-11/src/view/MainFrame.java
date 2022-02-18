package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    DrawingPanel drawingPanel;

    public MainFrame() {
        super();

        String savedFilePath = "./saved.txt";


        drawingPanel = new DrawingPanel();
        JScrollPane scrollablePanel = new JScrollPane(drawingPanel);
        scrollablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        drawingPanel.setPreferredSize(new Dimension(1920, 1080));
        this.add(scrollablePanel);
        this.setSize(1000, 800);

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
