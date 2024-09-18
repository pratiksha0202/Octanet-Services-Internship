import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private double balance;
    private int pin;
    private ArrayList<String> transactionHistory;

    // Constructor
    public ATM(double initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Initial balance: $" + initialBalance);
    }

    // Method to check account balance
    public void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
        transactionHistory.add("Checked balance: $" + balance);
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("You have withdrawn: $" + amount);
            System.out.println("Your new balance is: $" + balance);
            transactionHistory.add("Withdraw: $" + amount);
        } else {
            System.out.println("Insufficient balance. Transaction failed.");
            transactionHistory.add("Failed withdrawal attempt: $" + amount);
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        balance += amount;
        System.out.println("You have deposited: $" + amount);
        System.out.println("Your new balance is: $" + balance);
        transactionHistory.add("Deposited: $" + amount);
    }

    // Method to change the PIN
    public void changePin(int newPin) {
        this.pin = newPin;
        System.out.println("Your PIN has been successfully changed.");
        transactionHistory.add("Changed PIN");
    }

    // Method to show transaction history
    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Method to validate PIN
    public boolean validatePin(int enteredPin) {
        return enteredPin == pin;
    }
}

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM myATM = new ATM(1000.0, 1234); // Initial balance of $1000 and PIN 1234

        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (myATM.validatePin(enteredPin)) {
            int choice;
            do {
                System.out.println("\nATM Machine:");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Change PIN");
                System.out.println("5. Transaction History");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        myATM.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        myATM.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        myATM.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter new PIN: ");
                        int newPin = scanner.nextInt();
                        myATM.changePin(newPin);
                        break;
                    case 5:
                        myATM.showTransactionHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. May visit again!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);
        } else {
            System.out.println("Invalid PIN. Access denied.");
        }

        scanner.close();
    }
}
