package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class FieldPanel extends JPanel implements ActionListener {
    public static ArrayList<ArrayList<Cell>> table;
    public static ArrayList<Child> children;
    private static Santa santa;
    Field field;
    Game game;
    int rows;
    int columns;

    public FieldPanel(int rows, int columns, Game game) {
        super();
        this.field = new Field(rows, columns, this);
        this.rows = rows;
        this.columns = columns;
        this.game = game;

        CellImage images = new CellImage();

        table = new ArrayList<>();
        generateGrid();
        addSanta();
        addChildren();
        startTimer();

        int[] fieldSize = field.countFieldSize();
        setPreferredSize(new Dimension(fieldSize[0], fieldSize[1]));
        setLayout(new GridLayout(rows, columns));
    }

    private void generateGrid() {
        for (int i = 0; i < rows; i++) {
            table.add(i, new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                Cell cell = new Cell(i, j);
                table.get(i).add(j, cell);
                this.add(cell);
            }
        }
    }

    public Cell getCellByIndex(int row, int col) {
        return table.get(row).get(col);
    }

    private void addSanta() {
        Cell santaCell = getCellByIndex(10, 10);
        santa = new Santa(santaCell);
        santaCell.setSanta(santa);
    }

    private void addChildren() {
        Child.setTable(table);
        children = new ArrayList<>();
        while (children.size() != 13) {
            Random rand = new Random();
            int row = rand.nextInt(rows);
            int col = rand.nextInt(columns);
            Cell cell = getCellByIndex(row, col);
            if (cell.getContent() == ItemEnum.EMPTY) {
                Child child = new Child(cell, this);
                ChildThread childThread = new ChildThread(child);
                childThread.start();
                children.add(child);
                cell.setChild(child);
            }
        }
    }

    private void startTimer() {
        Timer timer = new Timer(40, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Field getField() {
        return field;
    }

    public static Santa getSanta() {
        return santa;
    }

    public Game getGame() {
        return game;
    }
}
