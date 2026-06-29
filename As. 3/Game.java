import java.util.Scanner;

public class Game{

    //member variables
    private String player1, player2;
    private int score1, score2;
    private int pot;
    private Dice dice1, dice2;
    private Scanner scanner;

    //constructor
    public Game(String p1, String p2, int sides){
        player1 = p1;
        player2 = p2;
        dice1 = new Dice(sides);
        dice2 = new Dice(sides);

        scanner = new Scanner(System.in); //user input
        reset();
    }

    //reset game
    public void reset(){
        score1 = 0;
        score2 = 0;
        pot = 0;
    }

    //check for primes 
    private boolean isPrime(int num){
        if (num <= 1) {
            return false;
        }

        for (int i = 2; i*i <= num; i++){
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }

    //check for doubles
    private boolean isDouble(int r1, int r2){
        return r1 == r2;
    }

    //game loop
    public void play(){
        boolean gameOver = false;
        boolean player1Turn = true;

        while (!gameOver){

            String currentPlayer;
            boolean turnOver = false;

            //whose turn is it?
            if (player1Turn){
                currentPlayer = player1;
            } else{
                currentPlayer = player2;
            }

            //player's turn
            while (!turnOver) {

                //print scores & the pot
                System.out.println(player1 + " Score: " + score1);
                System.out.println(player2 + " Score: " + score2);
                System.out.println("Pot: " + pot);

                //roll or pass?
                int choice = -1;

                while (choice != 0 && choice != 1){
                    System.out.print(currentPlayer + ": Roll (1) or Pass (0)?\nChoose: ");
                    choice = scanner.nextInt();

                    if(choice != 0 && choice != 1){
                        System.out.println("Invalid input. Please enter 1 to Roll or 0 to Pass.");
                    }
                }

                //rolls
                if (choice == 1){
                    int roll1 = dice1.roll();
                    int roll2 = dice2.roll();
                    int sum = roll1 + roll2;

                    //double prime?
                    if(isDouble(roll1, roll2) && isPrime(roll1)) {

                        System.out.println(
                            currentPlayer + " rolled a double (" +
                            roll1 + " and " + roll2 +
                            ")! The pot goes to your opponent"
                        );

                        if (player1Turn){
                            score2 += pot;
                        } else{
                            score1 += pot;
                        }

                        pot = 0;
                        turnOver = true;
                        
                        //update score
                        System.out.println(player1 + " Score: " + score1);
                        System.out.println(player2 + " Score: " + score2);
                        System.out.println("Pot: " + pot);

                    } else{

                        //typical roll
                        System.out.println(
                            currentPlayer + " rolled a " +
                            roll1 + " and a " + roll2 +
                            " (" + sum + ")"
                        );

                        pot += sum;
                    }
                }
                //player passes
                else{
                    System.out.println(currentPlayer + " ends their turn");

                    if (player1Turn){
                        score1 += pot;
                    } else {
                        score2 += pot;
                    }

                    pot = 0;
                    turnOver = true;
                }
            }

            //who won? score of 100
            if (score1 >= 100){
                System.out.println(player1 + " is the winner!");
                gameOver = true;
            } else if (score2 >= 100){
                System.out.println(player2 + " is the winner!");
                gameOver = true;
            } else {
                player1Turn = !player1Turn;
            }
        }
    }
}

