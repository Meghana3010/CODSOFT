import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public String deposit(double amount) {
        balance += amount;
        return String.format("Deposited $%.2f. New balance: $%.2f", amount, balance);
    }

    public String withdraw(double amount) {
        if (amount > balance) {
            return "Insufficient funds";
        } else {
            balance -= amount;
            return String.format("Withdrew $%.2f. New balance: $%.2f", amount, balance);
        }
    }

    public String checkBalance() {
        return String.format("Current balance: $%.2f", balance);
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("\n1. Withdraw\n2. Deposit\n3. Check Balance\n4. Exit");
    }

    public void runATM() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.println(account.withdraw(withdrawAmount));
                    break;

                case "2":
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.println(account.deposit(depositAmount));
                    break;

                case "3":
                    System.out.println(account.checkBalance());
                    break;

                case "4":
                    System.out.println("Exiting ATM. Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial account balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atmMachine = new ATM(userAccount);

        atmMachine.runATM();
    }
}
