package actions.game_actions;

import game.Game;
import labyrinth.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractAction {
    private final Game gameObj;

    public ExitAction(Game gameObj) {
        super("Exit");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        this.gameObj = gameObj;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        GameFrame gameFrame = gameObj.getGameFrame();
        gameFrame.dispose();
    }
}
