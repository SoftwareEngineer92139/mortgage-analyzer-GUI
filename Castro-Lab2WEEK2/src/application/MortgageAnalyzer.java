// MortgageAnalyzer.java
// MortgageAnalyzer class that contains amount of loan, the annual rate of interest, duration of the loan in months instance variables
// and methods to set and get its values as well as run verification check, correction, and calculation of mortgage details and display

package application;

import java.lang.Math;              // for exponential calculation
import java.util.Scanner;
import java.util.regex.Pattern;     // for data validation

public class MortgageAnalyzer {

    private int p;                  // principal amount of the loan
    private double i;               // annual rate of interest
    private double r;               // monthly interest rate
    private int n;                  // duration of the loan (number of pay periods)
    private double totalInterest;   // calculation of total interest for the loan
    private double monthlyPayment;  // calculation of monthly payment
    private Scanner input;

    // constructor initializes variables with parameters p and i
    public MortgageAnalyzer(int p, double i, int n)
    {
        this.p = p;
        this.i = i / 100.0;
        this.n = n;
        r = this.i / 12.0;               // calculation of monthly interest rate
    }

    // constructor with no parameters
    public MortgageAnalyzer()
    {
        p = 0;
        i = 0.0;
        n = 0;
        r = i / 12.0;
    }
    
    // method to reset values
    public void reset()
    {
        this.p = 0;
        this.i = 0.0;
        this.n = 0;
        r = i / 12.0;
    }

    // method to set the principal amount of the loan
    public void setp(int p)
    {
        this.p = p;
    }

    // method to set the annual rate of interest, which automatically sets the monthly interest rate
    public void seti(double i)
    {
        this.i = i / 100.0;
        r = i / 12.0;
    }

    // method to set the number of payments
    public void setn(int n)
    {
        this.n = n;
    }

    // method to retrieve the principal amount of the loan
    public double getp()
    {
        return p;
    }

    // method to retrieve the annual rate of interest
    public double geti()
    {
        return i;
    }

    // method to retrieve the number of payments
    public int getn()
    {
        return n;
    }

    // method to retrieve the monthly payment calculated
    public double getMonthlyPayment()
    {
        monthlyPayment = Math.round((p * r * (Math.pow((1 + r),n)) / (Math.pow((1 + r),n) - 1)) * 100.0) / 100.0;

        return monthlyPayment;
    }

    // method to retrieve the total interest
    public double getTotalInterest()
    {
        totalInterest = n * monthlyPayment - p;

        return totalInterest;
    }

    // method to calculate mortage details
    public void calculateMortgageDetails(int p, double i, int n)
    {  
        // calculate mortgage details based on validated values
        this.p = p;
        this.i = i / 100.0;
        r = this.i / 12.0;               // calculation of monthly interest rate
        this.n = n;
       
        // call the methods to calculate monthly payment and total interest
        double pmt = getMonthlyPayment();
        double interest = getTotalInterest();

        // nicely display information
        System.out.printf("Amount of Loan: $%d%n", p);
        System.out.printf("Annual Interest Rate: %.2f%%%n", this.i * 100);
        System.out.printf("Duration of loan in months: %d%n", n);
        System.out.printf("Monthly payment: $%.2f%n", pmt);
        System.out.printf("Total interest paid: $%.2f%n", interest);
    }
    
    // method to provide a means of validation check, returning true or false for validation
    public boolean verificationCheck(String loanAmount, String interestAmount, String loanDuration)
    {
        boolean validateLoanDetails = false;

        // setting up the pattern to validate data
        String patternLoan = "^\\d{1,6}$";                          // validate for at least 6 digits, no more for loan amount
        String patternInterest = "^\\d{1,2}(\\.\\d{1,2})?$";        // validate for two digits and two decimals, no more for interest rate
        String patternDuration = "^\\d{1,3}$";                      // validate for at least 3 digits, no more for loan duration

        // this validation verification will check all three pieces of information and all must be valid in order to proceed
        if(Pattern.matches(patternLoan, loanAmount) & Pattern.matches(patternInterest, interestAmount) & Pattern.matches(patternDuration, loanDuration))
        {
            validateLoanDetails = true;
        }
        else
        {
            validateLoanDetails = false;
        }

        return validateLoanDetails;
    }

    // method to provide validation check on specifically loan amount
    public boolean verifyLoanAmount(String loanAmount)
    {
        boolean validate = false;

        // setting up the pattern to validate loan amount
        String patternLoan = "^\\d{1,6}$";                          // validate for at least 6 digits, no more for loan amount

        // this validation will check just one piece of the information
        if(Pattern.matches(patternLoan, loanAmount))
        {
            validate = true;
        }
        else
        {
            validate = false;
        }

        return validate;

    }

    // method to provide validation check on specifically annual interest rate
    public boolean verifyInterestRate(String interestAmount)
    {
        boolean validate = false;

        // setting up the pattern to validate loan amount
        String patternInterest = "^\\d{1,2}(\\.\\d{1,2})?$";        // validate for two digits and two decimals, no more for interest rate

        // this validation will check just one piece of the information
        if(Pattern.matches(patternInterest, interestAmount))
        {
            validate = true;
        }
        else
        {
            validate = false;
        }

        return validate;

    }

    // method to provide validation check on specifically the duration of the loan
    public boolean verifyDuration(String loanDuration)
    {
        boolean validate = false;

        // setting up the pattern to validate loan amount
        String patternDuration = "^\\d{1,3}$";                      // validate for at least 3 digits, no more for loan duration

        // this validation will check just one piece of the information
        if(Pattern.matches(patternDuration, loanDuration))
        {
            validate = true;
        }
        else
        {
            validate = false;
        }

        return validate;

    }


    // method to enter information on the mortgage details
    public void enterMortgageInformation()
    {
        input = new Scanner(System.in);

         // prompt for user to enter variables
        System.out.println("Enter the amount of the loan: ");
        String amount = input.nextLine();
        System.out.println("Enter the annual interest rate (omit the % sign): ");
        String annualInterest = input.nextLine();
        System.out.println("Enter the duration of loan in months: ");
        String duration = input.nextLine();

        // call for validation check
        if(verificationCheck(amount,annualInterest,duration))
        {
            System.out.println("data validation successful");
            p = Integer.parseInt(amount);
            i = Double.parseDouble(annualInterest);
            n = Integer.parseInt(duration);
            calculateMortgageDetails(p, i, n);
        }
        else
        {
            System.out.println("sorry, check your data again!");
            correctInput();
        }
    }

    // method to allow correction on data to be made by calling again the enter information method again until data is correctly validated
    public void correctInput()
    {
        enterMortgageInformation();
    }

}
