package com.company;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Stack<Memento> hist = new Stack<Memento>();
        Board b = new Board();
        hist.add(b.save());
        int input = 0;
        char currToken = ' ';
        int counter = 1;
        boolean flag = true;
        while (flag) {
            b.show();
                System.out.println("\n1. Place value\n2. Return\n0. Quit");
                input = inputRange(0, 2);
                if (input == 1) {
                    System.out.print("Place an ");
                    if (counter % 2 == 1) {
                        currToken = 'x';
                        System.out.println("x");
                    } else {
                        currToken = 'o';
                        System.out.println("o");
                    }
                    System.out.print("Row: ");
                    int row = inputRange(0, 2);
                    System.out.print("Col: ");
                    int column = inputRange(0, 2);
                    System.out.println();
                    if (b.checkPlace(currToken, row, column)) {
                        hist.push(b.save());
                        counter++;
                    }
                    if(b.checkWinner(counter)) {
                        if ((currToken == 'x' || currToken == 'o') && counter >= 4)
                            b.show();
                            System.out.printf("\nPlayer  - %c - win\n", currToken);
                        flag = false;
                }
            }
            else if (input == 2) {
                try {
                    b.restore(hist.pop());
                    counter--;
                } catch(EmptyStackException e) {
                    System.out.println("\nNo previous moves\n");
                }
            }
            else if (input == 0) {
                flag = false;
            }
        }
    }

    public static int inputRange (int a, int b) {
        Scanner in = new Scanner(System.in);
        int input = 0;
        boolean flag = false;
        while (!flag) {
            if (in.hasNextInt()) {
                input = in.nextInt();
                if (input <= b && input >= a) {
                    flag = true;
                }
                else {
                    System.out.println("\nInvalid Range\n");
                }
            }
            else {
                in.next();
                System.out.println("\nInvalid Input\n");
            }
        }
        return input;
    }
}
