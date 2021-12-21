package labyrinth;

import actions.moves_actions.MoveAction;
import game.Direction;
import game.Game;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameFrame extends JFrame implements KeyListener {
    Game game;
    JPanel mazePanel;
    JPanel mazeContainer;
    JPanel functionalityPanel;
    JTextPane textPane;
    Maze maze;

    public JTextPane getTextPane() {
        return textPane;
    }


    public GameFrame(Game game) {
        super();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.game = game;
        functionalityPanel = new JPanel(new GridBagLayout());
        mazeContainer = new JPanel(new GridBagLayout());
        addTextPane();
        addMenuBar();
        addControls();


        this.add(mazeContainer);
        this.add(functionalityPanel);
        this.setLayout(new GridLayout(1,2));
        this.setSize(1920, 800);
        this.setVisible(true);

    }

    private void paintMaze(Color wallsColor) {
        mazePanel = new JPanel();
        for (ArrayList<Cell> row : Maze.table) {
            for (Cell cell : row) {
                mazePanel.add(cell);
                setWalls(cell, wallsColor);
                cell.repaint();
            }
        }
    }



    public void addMaze(Maze maze, Color wallsColor) {
        this.maze = maze;
        paintMaze(wallsColor);
        int mazeSize = maze.getMazeSize();
        mazePanel.setSize(600, 600);
        mazePanel.setLayout(new GridLayout(mazeSize, mazeSize));
        mazePanel.setPreferredSize(new Dimension(600, 600));
        mazeContainer.removeAll();
        mazeContainer.repaint();
        mazeContainer.add(mazePanel);
    }

    public void removeMaze() {
        mazeContainer.remove(mazePanel);
        mazeContainer.repaint();
    }

    private void setWalls(Cell cell, Color wallsColor) {
        int topWall = cell.getTopWall();
        int bottomWall = cell.getBottomWall();
        int rightWall = cell.getRightWall();
        int leftWall = cell.getLeftWall();

        cell.setBorder(BorderFactory.createMatteBorder(topWall, leftWall, bottomWall, rightWall, wallsColor));

    }

    private void addTextPane() {
        textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        textPane.setText("PRESS CTR+S TO START A GAME");
        textPane.setPreferredSize(new Dimension(345, 100));
        textPane.setEditable(false);
        textPane.setFont(new Font("Serif", Font.BOLD, 22));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        functionalityPanel.add(textPane, gridBagConstraints);
    }

    private void addMenuBar() {
        MenuBar menuBar = new MenuBar(game, textPane);
        this.setJMenuBar(menuBar);
    }

    private void addControls() {
        JPanel controlsContainer = new JPanel();
        JButton forwardButton = new JButton("FORWARD");
        JButton backwardsButton = new JButton("BACKWARDS");
        JButton upButton = new JButton("UP");
        JButton downButton = new JButton("DOWN");

        controlsContainer.add(forwardButton);
        controlsContainer.add(backwardsButton);
        controlsContainer.add(upButton);
        controlsContainer.add(downButton);

        downButton.addActionListener(new MoveAction(game, Direction.DOWN));
        downButton.setFocusable(false);

        upButton.addActionListener(new MoveAction(game, Direction.UP));
        upButton.setFocusable(false);

        forwardButton.addActionListener(new MoveAction(game, Direction.FORWARD));
        forwardButton.setFocusable(false);

        backwardsButton.addActionListener(new MoveAction(game, Direction.BACKWARDS));
        backwardsButton.setFocusable(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        functionalityPanel.add(controlsContainer, gridBagConstraints);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_RIGHT)
            game.makeMove(Direction.FORWARD);
        else if(e.getKeyCode()== KeyEvent.VK_LEFT)
            game.makeMove(Direction.BACKWARDS);
        else if(e.getKeyCode()== KeyEvent.VK_DOWN)
            game.makeMove(Direction.DOWN);
        else if(e.getKeyCode()== KeyEvent.VK_UP)
            game.makeMove(Direction.UP);
    }

    public void repaintWalls(Maze maze, Color color) {
        addMaze(maze, color);
    }

    public void repaintBackground(Color color) {
        mazeContainer.setBackground(color);
        functionalityPanel.setBackground(color);
        for (ArrayList<Cell> row : Maze.table) {
            for (Cell cell : row) {
                cell.setBackground(color);
            }
        }
        markStart();
        markFinish();
    }

    public void markStart() {
        Cell startCell = maze.getCell(0, 0);
        startCell.mark();
    }

    public void markFinish() {
        Cell finishCell = maze.finishCell;
        finishCell.setBackground(Color.CYAN);
    }

}
