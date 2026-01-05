package banking.app;

import banking.model.*;
import banking.service.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
        ATMService atmService = new ATMService();
        Session session = new Session();

        // Demo users
        authService.register(new User("RAYAN", "1234", "CUSTOMER"));
        authService.register(new User("ALI", "5678", "CUSTOMER"));

        boolean appRunning = true;

        while (appRunning) {

            System.out.println("\n=== LOGIN ===");
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = authService.login(username, password);

            if (user == null) {
                System.out.println("Invalid username or password.");
                continue;
            }

            session.login(user);
            System.out.println("Login successful.");

            // Accounts for this session
            Account savings = new SavingsAccount("SA-" + username);
            Account checking = new CheckingAccount("CA-" + username, 2.0);

            boolean loggedIn = true;

            while (loggedIn) {

                System.out.println("\n--- MENU ---");
                System.out.println("1 - Deposit");
                System.out.println("2 - Withdraw");
                System.out.println("3 - Transfer");
                System.out.println("4 - View Transactions");
                System.out.println("5 - Export CSV");
                System.out.println("6 - Logout");
                System.out.print("Choice: ");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                    continue;
                }

                switch (choice) {

                    // ===== DEPOSIT =====
                    case 1 -> {
                        System.out.print("Amount to deposit: ");
                        double amount = Double.parseDouble(scanner.nextLine());

                        if (amount <= 0) {
                            System.out.println("Deposit amount must be greater than 0.");
                        } else {
                            atmService.deposit(savings, amount);
                            System.out.println("Deposit successful.");
                        }

                        System.out.println("Current balance: " +
                                atmService.checkBalance(savings));
                    }

                    // ===== WITHDRAW =====
                    case 2 -> {
                        System.out.print("Amount to withdraw: ");
                        double amount = Double.parseDouble(scanner.nextLine());

                        double balance = atmService.checkBalance(savings);

                        if (amount <= 0) {
                            System.out.println("Invalid amount.");
                        } else if (amount > balance) {
                            System.out.println("Insufficient balance. Withdrawal denied.");
                        } else {
                            atmService.withdraw(savings, amount);
                            System.out.println("Withdrawal successful.");
                        }

                        System.out.println("Current balance: " +
                                atmService.checkBalance(savings));
                    }

                    // ===== TRANSFER =====
                    case 3 -> {
                        System.out.print("Amount to transfer to checking: ");
                        double amount = Double.parseDouble(scanner.nextLine());

                        double balance = atmService.checkBalance(savings);

                        if (amount <= 0) {
                            System.out.println("Invalid amount.");
                        } else if (amount > balance) {
                            System.out.println("Insufficient balance. Transfer denied.");
                        } else {
                            atmService.transfer(savings, checking, amount);
                            System.out.println("Transfer successful.");
                        }

                        System.out.println("Savings balance: " +
                                atmService.checkBalance(savings));
                        System.out.println("Checking balance: " +
                                atmService.checkBalance(checking));
                    }

                    // ===== VIEW TRANSACTIONS =====
                    case 4 -> {
                        System.out.println("\nSavings Transactions:");
                        atmService.viewTransactions(savings);

                        System.out.println("\nChecking Transactions:");
                        atmService.viewTransactions(checking);

                        System.out.println("\nCurrent balances:");
                        System.out.println("Savings: " +
                                atmService.checkBalance(savings));
                        System.out.println("Checking: " +
                                atmService.checkBalance(checking));
                    }

                    // ===== EXPORT CSV =====
                    case 5 -> {
                        exportToCSV(
                                username,
                                savings.getTransactions(),
                                checking.getTransactions()
                        );
                    }

                    // ===== LOGOUT =====
                    case 6 -> {
                        session.logout();
                        loggedIn = false;
                        System.out.println("Logged out.");
                    }

                    default -> System.out.println("Invalid choice.");
                }
            }

            System.out.print("\nLogin with another user? (y/n): ");
            String again = scanner.nextLine();

            if (!again.equalsIgnoreCase("y")) {
                appRunning = false;
            }
        }

        scanner.close();
        System.out.println("Application closed.");
    }

    // ================= CSV EXPORT =================
    private static void exportToCSV(
            String username,
            List<Transaction> savingsTx,
            List<Transaction> checkingTx
    ) {

        String fileName = "transactions_" + username + ".csv";

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write("Account,Type,Amount,Date\n");

            for (Transaction t : savingsTx) {
                writer.write("SAVINGS," +
                        t.getType() + "," +
                        t.getAmount() + "," +
                        t.getDate() + "\n");
            }

            for (Transaction t : checkingTx) {
                writer.write("CHECKING," +
                        t.getType() + "," +
                        t.getAmount() + "," +
                        t.getDate() + "\n");
            }

            System.out.println("CSV exported: " + fileName);

        } catch (IOException e) {
            System.out.println("Error exporting CSV file.");
        }
    }
}
