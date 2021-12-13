package actions.settings_actions;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColorChangeAction extends AbstractAction {
    Game game;
    String element;
    Color color;

    public ColorChangeAction(Color color, Game game, String element) {
        super();
        this.game = game;
        this.element = element;
        this.color = color;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (element.equals("walls")) {
            game.getGameFrame().repaintWalls(game.getMaze(), color);
        }
        else {
            game.getGameFrame().repaintBackground(color);
        }
        SwingUtilities.updateComponentTreeUI(game.getGameFrame());
    }
}
