package actions.game_actions;

import game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GiveUpAction extends AbstractAction {
    private final Game gameObj;
    private final JTextPane textPane;

    public GiveUpAction(Game gameObj, JTextPane textPane) {
        super("Give Up");
        putValue(MNEMONIC_KEY, KeyEvent.VK_G);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        this.gameObj = gameObj;
        this.textPane = textPane;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        gameObj.giveUpGame();
        textPane.setText("YOU GAVE UP. GAME ENDED\nMOVES MADE: " + gameObj.getMovesMade());
    }
}
