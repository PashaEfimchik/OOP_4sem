package com.company;

public class Board {

    private char[][] board;

    public Board() {
        int n = 3;
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean checkPlace(char token, int row, int column) {
        if (board[row][column] == ' ') {
            board[row][column] = token;
            return true;
        }
        else {
            System.out.println("\nThis space is taken\n");
            return false;
        }
    }

    public void show() {
        System.out.println("  0 1 2");
        System.out.println("0 " + board[0][0] + " " + board[0][1] + " " + board[0][2]);
        System.out.println("1 " + board[1][0] + " " + board[1][1] + " " + board[1][2]);
        System.out.println("2 " + board[2][0] + " " + board[2][1] + " " + board[2][2]);
    }

    public boolean checkWinner(int counter) {
        if (counter >= 6) {
            if ((checkHorizontal() || checkVertical() || checkDiagonals())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontal() {
        for (int i = 0; i < 3; i++) {
            boolean res = true;
            for (int j = 1; j < 3; j++) {
                if (board[i][j] != ' ')
                res = board[i][j] == board[i][0];
                else
                    return false;
            }
            if (res)
                return true;
        }
        return false;
    }

    private boolean checkVertical() {
        for (int i = 0; i < 3; i++) {
            boolean res = true;
            for (int j = 1; j < 3; j++)
                if (board[i][j] != ' ')
                    res = board[j][i] == board[0][i];
            if (res)
                return true;
        }
        return false;
    }

    private boolean checkDiagonals() {
        boolean res = true;
        for (int i = 1; i < 3; i++)
            if (board[i][i] != ' ')
                res = board[i][i] == board[0][0];
        if (res)
            return true;
        res = true;
        for (int i = 1; i < 3; i++)
            if (board[i][i] != ' ')
                res = board[3 - i - 1][i] == board[3 - 1][0];
        return res;
    }

    public Memento save() {
        return new Memento(board);
    }

    public void restore(Memento mem) {
        board = mem.getState();
    }
}
