package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    FieldPanel fieldPanel;
    MenuPanel menuPanel;
    MenuBar menuBar;
    Game game;

    public MainFrame() {
        game = new Game(this);

        createMenuPanel();
        createMenuBar();

        showMenuPanel();

        this.setSize(1900, 800);
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
    }

    public void createFieldPanel(int rows, int columns) {
        fieldPanel = new FieldPanel(rows, columns, game);
    }

    public void showFieldPanel() {
        this.remove(menuPanel);
        this.add(fieldPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void createMenuPanel() {
        menuPanel = new MenuPanel(game);
    }

    private void showMenuPanel() {
        this.add(menuPanel);
    }

    private void createMenuBar() {
        menuBar = new MenuBar();
    }

    public FieldPanel getFieldPanel() {
        return fieldPanel;
    }
}
