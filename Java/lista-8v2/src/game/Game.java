package game;

import labyrinth.Cell;
import labyrinth.GameFrame;
import labyrinth.Maze;

import java.awt.*;


public class Game {
    Maze maze;
    GameFrame gameFrame;
    Cell currCell;
    Cell nextCell;
    int movesMade = 0;

    public Game() {
        initiateGame();
    }

    private void initiateGame() {
        gameFrame = new GameFrame(this);
    }

    public void startGame() {
        startGame(20);
    }
    public void startGame(int mazeSize) {
        movesMade = 0;
        maze = new Maze(mazeSize);
        gameFrame.addMaze(maze, Color.black);
        currCell = maze.getCell(0, 0);
    }

    public void giveUpGame() {
        gameFrame.removeMaze();
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    private int[] getTargetIndex(Direction direction) {
        int currCellRowIndex = currCell.getRowIndex();
        int currCellColIndex = currCell.getColumnIndex();

        return switch (direction) {
            case UP -> new int[]{currCellRowIndex - 1, currCellColIndex};
            case DOWN -> new int[]{currCellRowIndex + 1, currCellColIndex};
            case FORWARD -> new int[]{currCellRowIndex, currCellColIndex + 1};
            case BACKWARDS -> new int[]{currCellRowIndex, currCellColIndex - 1};
        };
    }

    private boolean isMoveValid(Direction direction) {
        int[] nextCellIndex = getTargetIndex(direction);

        for (int num : nextCellIndex) {
            if (num > maze.getMazeSize() - 1 || num < 0) {
                return false;
            }
        }
        nextCell = maze.getCell(nextCellIndex[0], nextCellIndex[1]);
        return switch (direction) {
            case UP -> currCell.getTopWall() == 0 && nextCell.getBottomWall() == 0;
            case DOWN -> currCell.getBottomWall() == 0 && nextCell.getTopWall() == 0;
            case BACKWARDS -> currCell.getLeftWall() == 0 && nextCell.getRightWall() == 0;
            case FORWARD -> currCell.getRightWall() == 0 && nextCell.getLeftWall() == 0;
        };
    }

    public void makeMove(Direction direction) {
        if (isMoveValid(direction)) {
            currCell.unmark();
            nextCell.mark();
            currCell = nextCell;
            movesMade += 1;
            if (currCell == maze.getFinishCell()) {
                endGameWithVictory();
            }
        }
    }

    private void endGameWithVictory() {
        String message = "YOU WON\nMOVES MADE: " + movesMade;
        gameFrame.getTextPane().setText(message);

    }

    public int getMovesMade() {
        return movesMade;
    }

    public Maze getMaze() {
        return maze;
    }
}
