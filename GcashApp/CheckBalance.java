package GcashApp;

import java.util.ArrayList;

public class CheckBalance {

    private ArrayList<Balance> balances;

    public CheckBalance() {

        balances = new ArrayList<>();

        balances.add(new Balance(1, 5000, 1));
        balances.add(new Balance(2, 2500, 2));
        balances.add(new Balance(3, 10000, 3));
    }

    public ArrayList<Balance> getBalances() {
        return balances;
    }

    public double checkBalance(int userId) {

        for (Balance balance : balances) {

            if (balance.getUserId() == userId) {
                return balance.getAmount();
            }
        }

        return 0;
    }
}