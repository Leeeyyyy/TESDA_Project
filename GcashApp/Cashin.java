package GcashApp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cashin {

    private ArrayList<Transaction> transactions;
    private ArrayList<Balance> balances;
    private int nextTransactionId = 1;
    public ArrayList<Transaction> getTransactions() {
            return transactions;
    }
    public Cashin(ArrayList<Balance> balances) {
        this.balances = balances;
        this.transactions = new ArrayList<>();
    }

    public void cashIn(int userId, double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        for (Balance balance : balances) {

            if (balance.getUserId() == userId) {

                balance.setAmount(
                        balance.getAmount() + amount);

                Transaction transaction =
                        new Transaction(
                                nextTransactionId++,
                                amount,
                                "Cash In",
                                userId,
                                LocalDateTime.now(),
                                0,
                                0
                        );

                transactions.add(transaction);

                System.out.println("Cash In Successful!");
                System.out.println("Amount: " + amount);
                System.out.println("New Balance: "
                        + balance.getAmount());

                return;
            }
        }

        System.out.println("Account not found.");
    }
    public Object getTransaction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTransaction'");
    }
}