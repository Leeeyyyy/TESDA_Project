package GcashApp;


import java.util.ArrayList;

public class Transactions {

    private ArrayList<Transaction> transactions;

    public Transactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void viewAll() {

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {

            System.out.println("\n====================");
            System.out.println("Transaction ID: " + transaction.getId());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("Type: " + transaction.getName());
            System.out.println("Account ID: " + transaction.getAccountId());
            System.out.println("Date: " + transaction.getDate());
        }
    }

    public void viewUserAll(int userId) {

        boolean found = false;

        for (Transaction transaction : transactions) {

            if (transaction.getAccountId() == userId) {

                found = true;

                System.out.println("\n====================");
                System.out.println("Transaction ID: " + transaction.getId());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("Type: " + transaction.getName());
                System.out.println("Date: " + transaction.getDate());
            }
        }

        if (!found) {
            System.out.println("No transactions found for this user.");
        }
    }

    public void viewTransaction(int transactionId) {

        for (Transaction transaction : transactions) {

            if (transaction.getId() == transactionId) {

                System.out.println("\n====================");
                System.out.println("Transaction ID: " + transaction.getId());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("Type: " + transaction.getName());
                System.out.println("Account ID: " + transaction.getAccountId());
                System.out.println("Date: " + transaction.getDate());

                return;
            }
        }

        System.out.println("Transaction not found.");
    }
}
