import java.util.Scanner;

public class LoanCalculator{
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    //welcome message
    System.out.println("Welcome to the Mortgage Calculator!");

    System.out.print("Enter the loan amount: $"); //print because I want the value to appear on the same line
    //get user input for loan amount (int)
    int loanAmount = scanner.nextInt();

    System.out.print("Enter the interest rate (e.g 3.5 = 3.5%): ");
    //get user input for interest rate (double)
    double interestRate = scanner.nextDouble();

    System.out.print("Enter the loan length in years: ");
    //get user input for loan length (int)
    int loanLength = scanner.nextInt();

    //calcuate monthly payment using rP/1-(1+r)^-n
    //r is monthly rate, P is principle, n is loan length in months

    double monthlyRate = interestRate / 100 / 12; 
    int loanLengthInMonths = loanLength * 12;
    double num = monthlyRate * loanAmount;

    //represent given formula (num/denom)
    //math.pow gives exponents 
    double denom = 1 - Math.pow(1 + monthlyRate, -loanLengthInMonths);
    double monthlyPayment = num / denom;

    System.out.printf("Monthly Payment: $%.2f", monthlyPayment); //.2f is 2 decimal places
    scanner.close();

}
    }