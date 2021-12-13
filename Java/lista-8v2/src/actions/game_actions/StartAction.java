package actions.game_actions;

import game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class StartAction extends AbstractAction {
    private final Game gameObj;
    private final JTextPane textPane;

    public StartAction(Game gameObj, JTextPane textPane) {
        super("Start");
        putValue(MNEMONIC_KEY, KeyEvent.VK_S);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        this.gameObj = gameObj;
        this.textPane = textPane;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        gameObj.startGame();
        textPane.setText("GAME STARTED");
    }
}
