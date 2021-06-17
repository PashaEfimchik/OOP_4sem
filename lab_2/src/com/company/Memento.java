package com.company;

public class Memento {

    private char[][] board;

    public Memento (char[][] b) {
        int n = 3;
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = b[i][j];
            }
        }
    }

    public char[][] getState() {
        return board;
    }
}
