import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Assignment5 {
    //loads of parameters for sqrt_recur
    public static double sqrt_recur(double x, 
        double tolerance, 
        double guess, 
        PrintWriter out, 
        int iteration
    ) throws ImaginaryNumberException{

        //stop if x is negative
        //can't start recursion if input is invalid
        //handle exception
        if (x<0){
            throw new ImaginaryNumberException("Cannot take square root of a negative number: " + x);
        }

        //find difference w/ Newton's method (square, subtract, take abs value)
        double diff = Math.abs(guess * guess - x);

        //write to file
        out.println(iteration + ": " + guess + ", " + diff);

        //base case, stop when difference is <= tolerance
        if (diff <= tolerance){
            return guess;
        }
        //recursion - get closer
        double newGuess = 0.5 * (guess + (x / guess));
        return sqrt_recur(x, tolerance, newGuess, out, iteration + 1);
    }

    //main
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);

        System.out.print("Enter a value to find the square root of: ");
        double x = kb.nextDouble();

        System.out.print("Enter the tolerance: ");
        double tolerance = kb.nextDouble();

        System.out.print("Enter your initial guess: ");
        double guess = kb.nextDouble();

        try (PrintWriter out = new PrintWriter(new FileOutputStream("results.txt"))){
            double approx = sqrt_recur(x, tolerance, guess, out, 0);
            System.out.println("The square root of " + x + " to a tolerance of "
                + tolerance + " is " + approx);
            System.out.println("Results printed to the \"results.txt\" file.");
            
        //exceptions
        } catch (ImaginaryNumberException e){
            System.out.println("Error: " + e.getMessage());

        } catch (FileNotFoundException e){
            System.out.println("Error: Could not create/write results.txt");

        } finally {
            kb.close();
        }

    }       

}

//recusion and exceptions to implement Newton's method to approximate square root of a number
//use file output to print results
//initial guess, repeat

//write recursive method called sqrt_recur that takes value to find square root of x
//and initial guess, as doubles, a PrintWriter, and an int representing the iteration count
//return a double
//use recursive solution, not iterative solution
//for each sucessive value, write the iteration, the guess, and the difference to the file given by the PrintWriter

//throw exception if value to find square root is negative
//ImaginaryNumberException exception class that inherits from Exception class
//create constructor that takes in a string for the message, and call the super class constructor with the message

//modify sqrt_recur method to throw ImaginaryNumberException
//if input is negative, throw exception

//write main that asks users for a value to find the sqaure root of
//the tolerance, and an initial guess
//calculate the approx. and store results in file results.txt
//when done, print message letting user know where results are being stored


        
