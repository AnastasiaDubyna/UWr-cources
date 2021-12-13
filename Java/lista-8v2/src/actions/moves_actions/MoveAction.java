package actions.moves_actions;

import game.Direction;
import game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Locale;

public class MoveAction extends AbstractAction {
    private final Game gameObj;
    Direction direction;

    public MoveAction(Game gameObj, Direction direction) {
        super("Move " + direction.toString().toLowerCase(Locale.ROOT));
        this.gameObj = gameObj;
        this.direction = direction;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        gameObj.makeMove(direction);
    }
}
