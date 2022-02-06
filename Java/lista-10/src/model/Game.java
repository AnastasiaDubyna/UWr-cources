package model;

import view.MainFrame;


public class Game {
    MainFrame mainFrame;
    int rows;
    int columns;

    public Game(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void startNewGame(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        mainFrame.createFieldPanel(rows, columns);
        mainFrame.showFieldPanel();
    }

    public static void endGameWithDefeat() {

    }



}
