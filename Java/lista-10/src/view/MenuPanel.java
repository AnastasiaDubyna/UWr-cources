package view;

import model.Game;

import javax.swing.*;

public class MenuPanel extends JPanel {
    Game game;
    JTextField rowTextField;
    JTextField columnTextField;

    public MenuPanel(Game game) {
        this.game = game;
        addSizeTextField();
        addStartButton();

    }

    private void addSizeTextField() {
        JLabel label = new JLabel("CHOOSE FIELD SIZE");
        rowTextField = new JTextField(5);
        columnTextField = new JTextField( 5);

        this.add(label);
        this.add(rowTextField);
        this.add(columnTextField);
    }

    private void addStartButton() {
        StartButton startButton = new StartButton(game, rowTextField, columnTextField);

        this.add(startButton);
    }
}
