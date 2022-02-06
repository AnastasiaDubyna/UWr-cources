package model;

import view.Cell;
import view.FieldPanel;

import java.util.*;

public class Child {
    Cell cell;
    ActivityEnum activity;
    FieldPanel fieldPanel;
    public static ArrayList<ArrayList<Cell>> table;
    Field field;
    Santa santa;

    public Child(Cell cell, FieldPanel fieldPanel) {
        this.cell = cell;
        this.fieldPanel = fieldPanel;
        this.field = fieldPanel.getField();
        this.santa = FieldPanel.getSanta();
        activity = ActivityEnum.CATCHING;
    }

    public void catchSanta() {
        setNewTimer();
        while(activity != ActivityEnum.PLAYING) {
            if (activity == ActivityEnum.SLEEPING) {
                setNewTimer();
                activity = ActivityEnum.WAITING;
                checkForPresent();
            } else if (isSantaClose()) {
                activity = ActivityEnum.CATCHING;
                Cell santaCell = santa.getCell();
                moveToSanta(santaCell);
            } else {
                moveToRandomCell();
            }

        }
    }

    void setNewTimer() {
        int activityTime = getRandomTimeInterval();
        int sleepTime = getRandomTimeInterval();

        TimerTask goToSleepTask = new TimerTask() {
            public void run() {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(goToSleepTask, activityTime);
    }

    void moveToSanta(Cell santaCell) {
        int santaRow = santaCell.getRow();
        int santaCol = santaCell.getColumn();
        int childRow = cell.getRow();
        int childCol = cell.getColumn();

        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (Child.class) {
            if (santaRow > childRow) {
                changeCell(childRow + 1, childCol);
            } else if (santaRow < childRow) {
                changeCell(childRow - 1, childCol);
            } else {
                if (santaCol > childCol) {
                    changeCell(childRow, childCol + 1);
                } else if (santaCol < childCol) {
                    changeCell(childRow, childRow - 1);
                } else {
                    activity = ActivityEnum.CAUGHT_SANTA;
                    Game.endGameWithDefeat();
                }
            }
        }
    }

    void moveToRandomCell() {
        int row = cell.getRow();
        int column = cell.getColumn();
        int[][] possibleSpots = new int[][] {
                {row + 1, column},
                {row + 1, column + 1},
                {row, column + 1},
                {row - 1, column + 1},
                {row - 1, column},
                {row - 1, column - 1},
                {row, column - 1},
                {row + 1, column - 1}
        };
        int randomIndex = new Random().nextInt(possibleSpots.length);
        int newCellRow = possibleSpots[randomIndex][0];
        int newCellColumn = possibleSpots[randomIndex][1];

        while(!field.doesCellExist(newCellRow, newCellColumn)) {
            randomIndex = new Random().nextInt(possibleSpots.length);
            newCellRow = possibleSpots[randomIndex][0];
            newCellColumn = possibleSpots[randomIndex][1];
        }

        changeCell(newCellRow, newCellColumn);
    }

    void checkForPresent() {
        int row = cell.getRow();
        int column = cell.getColumn();

        int[][] possibleSpots = new int[][] {
                {row + 1, column},
                {row + 1, column + 1},
                {row, column + 1},
                {row - 1, column + 1},
                {row - 1, column},
                {row - 1, column - 1},
                {row, column - 1},
                {row + 1, column - 1}
        };

        for (int[] spot : possibleSpots) {
            if (field.doesCellExist(spot[0], spot[1]) && fieldPanel.getCellByIndex(spot[0], spot[1]).getContent() == ItemEnum.PRESENT) {
                activity = ActivityEnum.PLAYING;
            }
        }
    }

    private boolean isSantaClose() {
        return santa.getCell().getRow() - cell.getRow() <= 5 && santa.getCell().getColumn() - cell.getColumn() <= 5;
    }

    public static void setTable(ArrayList<ArrayList<Cell>> table) {
        Child.table = table;
    }

    int getRandomTimeInterval() {
        return (int) (Math.random() * 10000 * 2);
    }

    void changeCell(int newCellRow, int newCellCol) {
        Cell newCell = fieldPanel.getCellByIndex(newCellRow, newCellCol);
        if (newCell.getContent() == ItemEnum.EMPTY) {
            cell.removeChild();
            cell = newCell;
            cell.setChild(this);
        }
    }
}
