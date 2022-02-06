package controller;

import model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartAction extends AbstractAction {
    Game game;
    JTextField rowTextField;
    JTextField columnTextField;
    public StartAction(Game game, JTextField rowTextField, JTextField columnTextField) {
        super("START NEW GAME");
        this.game = game;
        this.rowTextField = rowTextField;
        this.columnTextField = columnTextField;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int rows = Integer.parseInt(rowTextField.getText());
        int columns = Integer.parseInt(columnTextField.getText());
        game.startNewGame(rows, columns);

    }
}
