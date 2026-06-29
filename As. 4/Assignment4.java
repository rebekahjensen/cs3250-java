//main
import java.util.Scanner; 

public class Assignment4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in); //user input
        TicTacToe game = new TicTacToe(scanner); //create new game

        //extra credit section
        System.out.println("Do you want to play against a computer? Please type Yes or No: ");
        boolean playComputer = scanner.nextLine().trim().equalsIgnoreCase("yes"); //case

        boolean playAgain = true;

        while(playAgain){

            if (playComputer){
                game.playComputer();
            } else {
            game.playGame(); 
            }

            playAgain = askPlayerAgain(scanner);

            if(playAgain){
                game.resetGame();
            }

        }

        System.out.println("Thanks for playing! Adios!");
        scanner.close();
    }

    private static boolean askPlayerAgain(Scanner scanner){

        while (true){

            System.out.print("Do you want to play again? Please type Yes or No: ");
            String response = scanner.nextLine().trim().toLowerCase(); //regardless of type case

            if (response.equals("yes")) { //lowercase anyway
                return true;
            } else if (response.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
            }

        }

    }

}
