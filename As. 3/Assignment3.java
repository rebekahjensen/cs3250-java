import java.util.Scanner;

public class Assignment3{ //where it all comes together
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int playAgain = 1;

        //begin
        while(playAgain ==1){
            System.out.println("Enter player 1's name: ");
            String player1 = scanner.nextLine();

            System.out.println("Enter player 2's name: ");
            String player2 = scanner.nextLine();

            System.out.println("Enter number of sides for the dice: ");
            int sides = scanner.nextInt();
            scanner.nextLine();
            
            //game object
            Game game = new Game(player1, player2, sides);
            game.play(); //begin

            //play again?
            playAgain = -1;
            while (playAgain !=0 && playAgain !=1){
                System.out.println("Play again? Yes (1), No (0): ");
                playAgain = scanner.nextInt();
                scanner.nextLine(); 

                if(playAgain !=0 && playAgain !=1){
                    System.out.println("Invalid input.");
                }
            }

            //reset game
            if(playAgain == 1){
                game.reset();
            }

        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}