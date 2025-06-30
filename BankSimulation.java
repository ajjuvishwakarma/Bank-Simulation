import java.util.ArrayList;
import java.util.Scanner;

// Account class for handling bank account related operations
class Account {
    private String accountHolder;                     // Account holder's name
    private double balance;                           // Current balance
    private ArrayList<String> transactionHistory;     // List to store transaction history

    // Constructor: Called when account is created
    public Account(String accountHolder) {
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    // Method to return current balance
    public double getBalance() {
        return balance;
    }

    // Deposit money to account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited ₹" + amount);
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money from account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn ₹" + amount);
            System.out.println("₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    // Show current balance
    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    // Show transaction history
    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Show complete bank statement
    public void showBankStatement() {
        System.out.println("\n----- Bank Statement -----");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance: ₹" + balance);
        showTransactionHistory();
    }

    // Transfer money from this account to another account
    public void transfer(Account receiver, double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            receiver.balance += amount;

            this.transactionHistory.add("Transferred ₹" + amount + " to " + receiver.accountHolder);
            receiver.transactionHistory.add("Received ₹" + amount + " from " + this.accountHolder);

            System.out.println("₹" + amount + " transferred to " + receiver.accountHolder + " successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}

// Main class with menu to use bank features
public class BankSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two accounts
        System.out.print("Enter name for Account1: ");
        Account acc1 = new Account(scanner.nextLine());

        System.out.print("Enter name for Account2: ");
        Account acc2 = new Account(scanner.nextLine());

        int choice;

        // Menu loop
        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Deposit Money (Account1)");
            System.out.println("2. Withdraw Money (Account1)");
            System.out.println("3. Check Balance (Account1)");
            System.out.println("4. Transaction History (Account1)");
            System.out.println("5. Bank Statement (Account1)");
            System.out.println("6. Transfer to Account2");
            System.out.println("7. Bank Statement (Account2)");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    acc1.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    acc1.withdraw(withdrawAmount);
                    break;

                case 3:
                    acc1.checkBalance();
                    break;

                case 4:
                    acc1.showTransactionHistory();
                    break;

                case 5:
                    acc1.showBankStatement();
                    break;

                case 6:
                    System.out.print("Enter amount to transfer to Account2: ₹");
                    double transferAmount = scanner.nextDouble();
                    acc1.transfer(acc2, transferAmount);
                    break;

                case 7:
                    acc2.showBankStatement();
                    break;

                case 8:
                    System.out.println("Thank you for using our Bank Simulation.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);

        scanner.close();
    }
}