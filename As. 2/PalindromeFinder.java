import java.util.Scanner;

public class PalindromeFinder{
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in); //user input

    //welcome message
    System.out.println("Welcome to the Palindrome Finder!");

    while (true){
        System.out.print("Enter a word or phrase or \"quit\" to quit: "); //prompt
        String userInput = scanner.nextLine(); //get user input

        if (userInput.equalsIgnoreCase("quit")){
            System.out.println("Goodbye!"); //user can quit program
            break;
        }
    
        //carry on with program
        String noSpaces = userInput.replace(" ", ""); //remove spaces from phrases to check multiple words
        boolean isPalindrome = true;

        //check if actually palindrome
        for (int i = 0; i < noSpaces.length() / 2; i++){
            if (Character.toLowerCase(noSpaces.charAt(i)) != Character.toLowerCase(noSpaces.charAt(noSpaces.length() - 1 - i))){
                isPalindrome = false;   
                break;
            }
        }

        //print results
        if (isPalindrome){
            System.out.println("\"" + userInput + "\" is a palindrome!");
        } else {
           System.out.println("\"" + userInput + "\" is not a palindrome.");   
            }
        }

        scanner.close();

    }
}