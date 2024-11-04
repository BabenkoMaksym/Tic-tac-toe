package org.example;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    private Scanner scanner;
    private String[][] board;
    private boolean win;

    public void start() {
        init();
        System.out.println("The numbers in the field correspond to the cell number you want to fill in.");
        printBoard();
        System.out.println("Let's play!");
        clearBoard();
        play();
    }

    private void init() {
        scanner = new Scanner(System.in);
        board = new String[][]{{"7", "8", "9"}, {"4", "5", "6"}, {"1", "2", "3"}};
        win = false;
    }

    private void clearBoard() {
        for (String[] row : board) {
            Arrays.fill(row, " ");
        }
    }


    private void printBoard() {
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " ");
    }

    private void checkWin(Player player, int moves) {
        if (!board[0][0].equals(" ") && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            win = true;
            player = player.getPlayer(board[0][0]);
        }
        if (!board[0][2].equals(" ") && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            win = true;
            player = player.getPlayer(board[0][2]);
        }

        if (!board[0][0].equals(" ") && board[0][0] == board[0][1] && board[0][0] == board[0][2]) {
            win = true;
            player = player.getPlayer(board[0][0]);
        }
        if (!board[1][0].equals(" ") && board[1][0] == board[1][1] && board[1][0] == board[1][2]) {
            win = true;
            player = player.getPlayer(board[1][0]);
        }
        if (!board[2][0].equals(" ") && board[2][0] == board[2][1] && board[2][0] == board[2][2]) {
            win = true;
            player = player.getPlayer(board[2][0]);
        }

        if (!board[0][0].equals(" ") && board[0][0] == board[1][0] && board[0][0] == board[2][0]) {
            win = true;
            player = player.getPlayer(board[0][0]);
        }
        if (!board[0][1].equals(" ") && board[0][1] == board[1][1] && board[0][1] == board[2][1]) {
            win = true;
            player = player.getPlayer(board[0][1]);
        }
        if (!board[0][2].equals(" ") && board[0][2] == board[1][2] && board[0][2] == board[2][2]) {
            win = true;
            player = player.getPlayer(board[0][2]);
        }
        if (win) {
            printBoard();
            System.out.println("Player " + player + " WIN!");
        } else if (moves == 0) {
            printBoard();
            System.out.println("The game ended in a draw.");
            win = true;
        }

    }

    private void play() {
        int number;
        int moves = 9;
        Player player = Player.X;
        while (!win) {
            printBoard();
            System.out.println("Player " + player + ". Enter a number from 1 to 9 to fill in the appropriate space on the board.");
            try {
                number = checkNumber(scanner.nextInt());
                if (checkField(number, player)) {
                    player = changePlayer(player);
                    moves--;
                } else {
                    System.out.println("Field is busy, please select another field");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.nextLine();
            }
            checkWin(player, moves);
        }
    }

    enum Player {
        X, O;

        public Player getPlayer(String player) {
            if (player == "X") {
                return X;
            }
            return O;
        }
    }

    private Player changePlayer(Player player) {
        switch (player) {
            case X -> player = Player.O;
            case O -> player = Player.X;
        }
        return player;
    }

    private int checkNumber(int number) {
        if (number >= 1 && number <= 9) {
            return number;
        } else {
            return 0;
        }
    }

    private boolean checkField(int number, Player player) {
        switch (number) {
            case 1:
                if (board[2][0].equals(" ")) {
                    board[2][0] = String.valueOf(player);
                    return true;
                }
                break;
            case 2:
                if (board[2][1].equals(" ")) {
                    board[2][1] = String.valueOf(player);
                    return true;
                }
                break;
            case 3:
                if (board[2][2].equals(" ")) {
                    board[2][2] = String.valueOf(player);
                    return true;
                }
                break;
            case 4:
                if (board[1][0].equals(" ")) {
                    board[1][0] = String.valueOf(player);
                    return true;
                }
                break;
            case 5:
                if (board[1][1].equals(" ")) {
                    board[1][1] = String.valueOf(player);
                    return true;
                }
                break;
            case 6:
                if (board[1][2].equals(" ")) {
                    board[1][2] = String.valueOf(player);
                    return true;
                }
                break;
            case 7:
                if (board[0][0].equals(" ")) {
                    board[0][0] = String.valueOf(player);
                    return true;
                }
                break;
            case 8:
                if (board[0][1].equals(" ")) {
                    board[0][1] = String.valueOf(player);
                    return true;
                }
                break;
            case 9:
                if (board[0][2].equals(" ")) {
                    board[0][2] = String.valueOf(player);
                    return true;
                }
                break;
        }
        return false;
    }

}
