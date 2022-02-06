package view;

import controller.StartAction;
import model.Game;

import javax.swing.*;

public class StartButton extends JButton {

    public StartButton(Game game, JTextField rowTextField, JTextField columnTextField) {
        super(new StartAction(game, rowTextField, columnTextField));
    }
}
