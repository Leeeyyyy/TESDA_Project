package GcashApp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserAuthentication auth = new UserAuthentication();
        CheckBalance cb = new CheckBalance();
        Cashin cashin = new Cashin(cb.getBalances());
        CashTransfer transfer = new CashTransfer(cb.getBalances());
        Transactions tx = new Transactions(cashin.getTransactions());

        int choice;

        do {

            System.out.println("\n===== GCASH APP =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Check Balance");
            System.out.println("4. Cash In");
            System.out.println("5. Cash Transfer");
            System.out.println("6. View My Transactions");
            System.out.println("7. Change PIN");
            System.out.println("8. Logout");
            System.out.println("9. Exit");

            System.out.print("Enter Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Mobile Number: ");
                    String number = scanner.nextLine();

                    System.out.print("PIN (4 digits): ");
                    String pin = scanner.nextLine();

                    auth.register(name, email, number, pin);

                    break;

                case 2:

                    System.out.print("Email: ");
                    email = scanner.nextLine();

                    System.out.print("PIN: ");
                    pin = scanner.nextLine();

                    auth.login(email, pin);

                    break;

                case 3:

                    if (auth.getCurrentUser() == null) {

                        System.out.println("Please login first.");

                    } else {

                        double balance = cb.checkBalance(
                                auth.getCurrentUser().getId());

                        System.out.println(
                                "Current Balance: ₱" + balance);
                    }

                    break;

                case 4:

                    if (auth.getCurrentUser() == null) {

                        System.out.println("Please login first.");

                    } else {

                        System.out.print("Enter amount to Cash In: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

                        cashin.cashIn(
                                auth.getCurrentUser().getId(),
                                amount);
                    }

                    break;

                case 5:

                    if (auth.getCurrentUser() == null) {

                        System.out.println("Please login first.");

                    } else {

                        System.out.print("Transfer To User ID: ");
                        int receiverId = scanner.nextInt();

                        System.out.print("Amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

                        transfer.cashTransfer(
                                auth.getCurrentUser().getId(),
                                receiverId,
                                amount
                        );
                    }

                    break;

                case 6:

                    if (auth.getCurrentUser() == null) {

                        System.out.println("Please login first.");

                    } else {

                        tx.viewUserAll(
                                auth.getCurrentUser().getId()
                        );
                    }

                    break;

                case 7:

                    if (auth.getCurrentUser() == null) {

                        System.out.println("Please login first.");

                    } else {

                        System.out.print("Current PIN: ");
                        String oldPin = scanner.nextLine();

                        System.out.print("New PIN: ");
                        String newPin = scanner.nextLine();

                        auth.changePin(oldPin, newPin);
                    }

                    break;

                case 8:

                    auth.logout();

                    break;

                case 9:

                    System.out.println("Thank you for using GCash App.");

                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while (choice != 9);

        scanner.close();
    }
}