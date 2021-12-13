package actions.settings_actions;

import game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SizeChangeAction extends AbstractAction {
    Game game;
    int size;
    JTextPane textPane;

    public SizeChangeAction(String name, int size, Game game, JTextPane textPane) {
        super(name);
        this.game = game;
        this.size = size;
        this.textPane = textPane;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        game.startGame(size);
        textPane.setText("GAME STARTED\n");
    }
}
