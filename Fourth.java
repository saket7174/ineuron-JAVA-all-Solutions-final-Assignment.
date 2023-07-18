
//4. wap to simulate a bank account .
//The program should allow users to deposit and withdraw money and also check Balance
import java.util.Scanner;

class BankAccount {
    private double balance;//it wills tore balance
    
    public BankAccount() {
        balance = 0.0;
    }
    
    public void deposit(double amount) {
        balance += amount;//it will take amount as parameter and add it to the balance
        System.out.println("Deposit successful.");
    }
    
    public void withdraw(double amount) {
        if (amount <= balance) {//it will check if the amount is <= balance
            balance -= amount;
            System.out.println("Withdrawal successful.");
        } else {//if no then else statement
            System.out.println("Insufficient funds.");
        }
    }
    
    public double getBalance() {
        return balance;//it will simply return the current balance 
    }
}

public class Fourth{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//it is for user input 
        BankAccount account = new BankAccount();
        
        while (true) {//it will present the menue of options
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1://for repeated action
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 4:
                    System.out.println("Exiting from ATM plz collect your recept,Thankyou visit Again...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            System.out.println();
        }
    }
}

