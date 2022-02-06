package model;

import view.Cell;
import view.FieldPanel;

public class Field {
    int rows;
    int columns;
    FieldPanel fieldPanel;

    public Field(int rows, int columns, FieldPanel fieldPanel) {
        this.rows = rows;
        this.columns = columns;
        this.fieldPanel = fieldPanel;
    }

    public int[] countFieldSize() {
        int defaultWidth = 1300;
        int maxHeight = 500;

        int cellWidth = defaultWidth / columns;
        int fieldHeight = cellWidth * rows;

        if (fieldHeight > maxHeight) {
            int diff = fieldHeight - maxHeight;
            diff /= rows;
            cellWidth -= diff;
            return new int[]{cellWidth * columns, maxHeight};
        }
        return new int[]{defaultWidth, fieldHeight};
    }

    public boolean doesCellExist(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }

    public boolean doesNearCellsContain(Cell cell, int range, ItemEnum item) {
        int rowIndex = cell.getRow();
        int columnIndex = cell.getColumn();
        return doesStraightLinesContain(rowIndex, columnIndex, range, item)
                || doesDiagonalsContain(rowIndex, columnIndex, range, item);
    }

    private boolean doesStraightLinesContain(int rowIndex, int columnIndex, int range, ItemEnum item) {
        for (int i = 1; i <= range; i++) {
            int[] newColumnIndexes = new int[]{columnIndex + i, columnIndex - i};
            for (int newColumnIndex : newColumnIndexes) {
                if (doesCellExist(rowIndex, newColumnIndex) && fieldPanel.getCellByIndex(rowIndex, newColumnIndex).getContent() == item) {
                    return true;
                }
            }
            int[] newRowIndexes = new int[]{rowIndex + i, rowIndex - i};
            for (int newRowIndex : newRowIndexes) {
                if (doesCellExist(newRowIndex, columnIndex) && fieldPanel.getCellByIndex(newRowIndex, columnIndex).getContent() == item) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean doesDiagonalsContain(int rowIndex, int columnIndex, int range, ItemEnum item) {
        for (int i = 1; i <= range; i++) {
            int[][] newIndexes = new int[][]{
                                            {rowIndex + i, columnIndex +  i},
                                            {rowIndex + i, columnIndex - i},
                                            {rowIndex - i, columnIndex + i},
                                            {rowIndex - i, columnIndex - i}
                                            };
            for (int[] indexPair : newIndexes){
                if (doesCellExist(indexPair[0], indexPair[1]) && fieldPanel.getCellByIndex(indexPair[0], indexPair[1]).getContent() == item) {
                    return true;
                }
            }
        }
        return false;
    }

}
