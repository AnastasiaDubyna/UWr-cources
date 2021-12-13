package labyrinth;

import actions.game_actions.ExitAction;
import actions.game_actions.GiveUpAction;
import actions.game_actions.StartAction;
import actions.moves_actions.MoveAction;
import actions.settings_actions.ColorChangeAction;
import actions.settings_actions.SizeChangeAction;
import game.Direction;
import game.Game;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
    Game game;
    JTextPane textPane;

    public MenuBar(Game game, JTextPane textPane) {
        super();
        this.game = game;
        this.textPane = textPane;

        addGameMenu();
        addMovesMenu();
        addSettingsMenu();
        JMenu helpMenu = new JMenu("Help");

        this.add(helpMenu);
    }

    private void addSettingsMenu() {
        JMenu settingsMenu = new JMenu("Settings");

        addSizeSubMenu(settingsMenu);
        addColorSubMenu(settingsMenu);

        this.add(settingsMenu);
    }

    private void addColorSubMenu(JMenu menu) {
        JMenu colorsSubMenu = new JMenu("Colors");
        JMenu backgroundColor = new JMenu("Background");
        JMenu wallsColor = new JMenu("Walls");

        Color[] colors = {Color.black, Color.pink, Color.yellow, Color.white, Color.lightGray, Color.red};
        for (Color color : colors) {
            JMenuItem bgMenuItem = new JMenuItem(new ColorChangeAction(color, game, "background"));
            bgMenuItem.setBackground(color);
            backgroundColor.add(bgMenuItem);
            JMenuItem wallsMenuItem = new JMenuItem(new ColorChangeAction(color, game, "walls"));
            wallsMenuItem.setBackground(color);
            wallsColor.add(wallsMenuItem);
        }

        colorsSubMenu.add(backgroundColor);
        colorsSubMenu.add(wallsColor);
        menu.add(colorsSubMenu);

    }

    private void addSizeSubMenu(JMenu menu) {
        JMenu sizeSubMenu = new JMenu("Maze Size");
        String name;

        for (int i = 4; i <= 24; i++) {
            name = i + "x" + i;
            JMenuItem sizeMenuItem = new JMenuItem(new SizeChangeAction(name, i, game, textPane));
            sizeSubMenu.add(sizeMenuItem);
        }
        menu.add(sizeSubMenu);
    }

    private void addGameMenu() {
        JMenu gameMenu = new JMenu("Game");

        JMenuItem startItem = new JMenuItem(new StartAction(game, textPane));
        JMenuItem giveUpItem = new JMenuItem(new GiveUpAction(game, textPane));
        JMenuItem exitItem = new JMenuItem(new ExitAction(game));


        gameMenu.add(startItem);
        gameMenu.add(giveUpItem);
        gameMenu.add(exitItem);

        this.add(gameMenu);
    }

    private void addMovesMenu() {
        JMenu movesMenu = new JMenu("Moves");

        JMenuItem moveUpItem = new JMenuItem(new MoveAction(game, Direction.UP));
        JMenuItem moveDownItem = new JMenuItem(new MoveAction(game, Direction.DOWN));
        JMenuItem moveForwardItem = new JMenuItem(new MoveAction(game, Direction.FORWARD));
        JMenuItem moveBackwardsItem = new JMenuItem(new MoveAction(game, Direction.BACKWARDS));

        movesMenu.add(moveForwardItem);
        movesMenu.add(moveBackwardsItem);
        movesMenu.add(moveUpItem);
        movesMenu.add(moveDownItem);

        this.add(movesMenu);
    }
}
