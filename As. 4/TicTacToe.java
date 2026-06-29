//game logic
import java.util.Scanner;
import java.util.Random; //in lecture u said not to use Random?? but I need it for extra credit...

public class TicTacToe {
    //variables
    private String[][] board; //game board
    private String currentPlayer; //current player
    private Scanner scanner; //user input
    private int moveCount; //count moves

    //constructor
    public TicTacToe(Scanner scanner) {
        this.scanner = scanner;
        board = new String[3][3];
        resetGame();
    }

    //reset game
    public void resetGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = "-";
            }
        }
        currentPlayer = "X"; //X always starts
        moveCount = 0;
    }

    //print it all out
    private void printBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j].equals("-")){
                    System.out.print("  ");
                } else{
                    System.out.print(board[i][j] + " ");
                }
                if (j < 2){
                    System.out.print("|");

                } 
            }
            System.out.println();
            if(i < 2){
                System.out.println("---------");
            }
        }
    }

    //switch player
    private void switchPlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }

    //valid row
    private int getValidRow() {
        while (true) {
            System.out.print("Choose a row (1-3): ");
            int row = scanner.nextInt();
            if (row >= 1 && row <= 3) {
                return row - 1;
            }
            System.out.println("Invalid choice.");
        }
    }

    //valid column
    private int getValidColumn() {
        while (true) {
            System.out.print("Choose a column (1-3): ");
            int col = scanner.nextInt();
            if (col >= 1 && col <= 3) {
                return col - 1;
            }
            System.out.println("Invalid choice.");
        }
    }

    //place symbol
    private void placeSymbol() {
        while (true) {
            int row = getValidRow();
            int col = getValidColumn();

            if (!board[row][col].equals("-")) {
                System.out.println("That spot is taken. Try again!");
            } else {
                board[row][col] = currentPlayer;
                moveCount++;
                break;
            }
        }
    }

    //check for winner
    private boolean checkWinner() {
        //rows & columns
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("-") &&
                board[i][0].equals(board[i][1]) &&
                board[i][1].equals(board[i][2])) {
                return true;
            }
            if (!board[0][i].equals("-") &&
                board[0][i].equals(board[1][i]) &&
                board[1][i].equals(board[2][i])) {
                return true;
            }
        }

        if (!board[0][0].equals("-") &&
            board[0][0].equals(board[1][1]) &&
            board[1][1].equals(board[2][2])) {
            return true;
        }

        if (!board[0][2].equals("-") &&
            board[0][2].equals(board[1][1]) &&
            board[1][1].equals(board[2][0])) {
            return true;
        }

        return false;
    }

    private boolean isStalemate() {
        return moveCount == 9 && !checkWinner();
    }

    //basic game versus two people
    public void playGame() {
        while (true) {
            System.out.println("Player " + currentPlayer + ", choose a spot!");
            placeSymbol();
            printBoard();

            if (checkWinner()) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isStalemate()) {
                System.out.println("Stalemate!");
                break;
            }

            switchPlayer();
        }
    }

    //extra credit: person versus computer
    public void playComputer() {
        Random rand = new Random();

        while (true) {

            if (currentPlayer.equals("X")) {
                System.out.println("Player X, choose a spot!");
                placeSymbol();
            } else {
                System.out.println("Computer is choosing a spot...");
                int row, col;
                do {
                    row = rand.nextInt(3);
                    col = rand.nextInt(3);
                } while (!board[row][col].equals("-"));

                board[row][col] = "O";
                moveCount++;
            }

            printBoard();

            if (checkWinner()) {
                if (currentPlayer.equals("O")) {
                    System.out.println("Computer wins!");
                } else {
                    System.out.println("Player X wins!");
                }
                break;
            }

            if (isStalemate()) {
                System.out.println("Stalemate!");
                break;
            }

            switchPlayer();
        }
    }
}



