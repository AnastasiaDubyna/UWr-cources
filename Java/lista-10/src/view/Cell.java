package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Cell extends JPanel {
    int row;
    int column;
    BufferedImage image;
    Image scaled;
    Santa santa;
    Child child;
    ItemEnum content;

    public Cell(int row, int column) {
        super();
        this.row = row;
        this.column = column;
        content = ItemEnum.EMPTY;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (image != null) {
            int width = getWidth();
            int height = getHeight();

            if (width > 0 && height > 0) {
                scaled = image.getScaledInstance(getWidth(), getHeight(),
                        Image.SCALE_SMOOTH);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return image == null ? new Dimension(200, 200) : new Dimension(
                image.getWidth(this), image.getHeight(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBorder(BorderFactory.createLineBorder(new Color(217, 217, 217)));
        setBackground(new Color(201, 250, 255));

        if (image != null) {
            g.drawImage(scaled, 0, 0, null);
        }
    }

    public void setChild(Child child) {
        this.child = child;
        content = ItemEnum.CHILD;
        image = CellImage.getImage(CellImageEnum.CHILD);
    }

    public void setSanta(Santa santa) {
        this.santa = santa;
        content = ItemEnum.SANTA;
        image = CellImage.getImage(CellImageEnum.SANTA);
    }

    public void removeChild() {
        child = null;
        content = ItemEnum.EMPTY;
        image = null;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ItemEnum getContent() {
        return content;
    }
}
