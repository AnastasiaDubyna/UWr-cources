package labyrinth;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    private int topWall;
    private int bottomWall;
    private int rightWall;
    private int leftWall;

    private boolean visited;

    private final int rowIndex;
    private final int columnIndex;


    public Cell(int rowIndex, int columnIndex) {
        topWall = 1;
        bottomWall = 1;
        rightWall = 1;
        leftWall = 1;

        visited = false;

        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void removeTopWall() {
        topWall = 0;
    }

    public int getTopWall() {
        return topWall;
    }

    public void removeBottomWall() {
        bottomWall = 0;
    }

    public int getBottomWall() {
        return bottomWall;
    }

    public void removeLeftWall() {
        leftWall = 0;
    }

    public int getLeftWall() {
        return leftWall;
    }

    public void removeRightWall() {
        rightWall = 0;
    }

    public int getRightWall() {
        return rightWall;
    }

    public void makeVisited() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public void mark() {
        this.setBackground(Color.BLUE);
    }

    public void unmark() {
        this.setBackground(Color.LIGHT_GRAY);
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        String filename = "ghost1.png";
//        ImageIcon icon = new ImageIcon(filename);
//        Image img = icon.getImage();
//
//        g.drawImage(image, 0, 0, null);
//        repaint();
//    }

}
