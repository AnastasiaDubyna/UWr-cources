package labyrinth;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Maze {

    static public ArrayList<ArrayList<Cell>> table;
    private final ArrayList<Cell> path;
    int mazeSize;
    Cell finishCell;

    public Maze(int mazeSize) {
        table = new ArrayList<>();
        path = new ArrayList<>();
        this.mazeSize = mazeSize;
        generateMaze();
    }

    private void generateGrid() {
        for (int i = 0; i < mazeSize; i++) {
            table.add(i, new ArrayList<>());
            for (int j = 0; j < mazeSize; j++) {
                table.get(i).add(j, new Cell(i, j));
            }
        }
    }

    public Cell getCell(int rowIndex, int colIndex) {
        return table.get(rowIndex).get(colIndex);
    }

    private void generateMaze() {
        table.clear();
        generateGrid();
        Cell currCell;
        ArrayList<Cell> unvisitedNeighbours;
        Cell randomNeighbour;
        int neighbourRowIndex;
        int neighbourColIndex;
        int currCellRowIndex;
        int currCellColIndex;

        finishCell = getCell(mazeSize - 1, mazeSize - 1);
        finishCell.setBackground(Color.CYAN);

        currCell = table.get(0).get(0);
        currCell.mark();
        currCell.makeVisited();
        path.add(currCell);

        while (!path.isEmpty()) {
            unvisitedNeighbours = getUnvisitedNeighbours(currCell);
            if (!unvisitedNeighbours.isEmpty()) {
                Collections.shuffle(unvisitedNeighbours);
                randomNeighbour = unvisitedNeighbours.get(0);

                neighbourRowIndex = randomNeighbour.getRowIndex();
                neighbourColIndex = randomNeighbour.getColumnIndex();
                currCellRowIndex = currCell.getRowIndex();
                currCellColIndex = currCell.getColumnIndex();

                if (neighbourColIndex > currCellColIndex) {
                    currCell.removeRightWall();
                    randomNeighbour.removeLeftWall();
                } else if (neighbourColIndex < currCellColIndex) {
                    currCell.removeLeftWall();
                    randomNeighbour.removeRightWall();
                } else if (neighbourRowIndex > currCellRowIndex) {
                    currCell.removeBottomWall();
                    randomNeighbour.removeTopWall();
                } else if (neighbourRowIndex < currCellRowIndex) {
                    currCell.removeTopWall();
                    randomNeighbour.removeBottomWall();
                }
                currCell = randomNeighbour;
                randomNeighbour.makeVisited();
                path.add(currCell);

            }
            else {
                int lastIndex = path.size() - 1;
                path.remove(lastIndex);
                lastIndex -= 1;
                currCell = lastIndex >= 0 ? path.get(lastIndex) : table.get(0).get(0);
            }
        }

    }

    private ArrayList<Cell> getUnvisitedNeighbours(Cell cell) {
        ArrayList<Cell> neighbours = new ArrayList<>();
        Cell newNeighbour;

        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();

        ArrayList<int[]> coordinatesToValidate = new ArrayList<>();
        Collections.addAll(coordinatesToValidate, new int[][]{
                {rowIndex - 1, columnIndex},
                {rowIndex + 1, columnIndex},
                {rowIndex, columnIndex + 1},
                {rowIndex, columnIndex - 1}});
        for (int[] coords : coordinatesToValidate) {
            if (isCoordsValid(coords)) {
                newNeighbour = getCell(coords[0], coords[1]);
                if (!newNeighbour.isVisited()) {
                    neighbours.add(newNeighbour);
                }
            }
        }

        return neighbours;
    }

    private boolean isCoordsValid(int[] coords) {
        for (int num: coords) {
            if (num > mazeSize - 1 || num < 0) {
                return false;
            }
        }
        return true;
    }

    public int getMazeSize() {
        return mazeSize;
    }

    public Cell getFinishCell() {
        return finishCell;
    }
}
