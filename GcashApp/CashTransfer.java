package GcashApp;

import java.util.ArrayList;

public class CashTransfer {

    private ArrayList<Balance> balances;

    public CashTransfer(ArrayList<Balance> balances) {
        this.balances = balances;
    }

    public void cashTransfer(int fromUserId,
                             int toUserId,
                             double amount) {

        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return;
        }

        if (fromUserId == toUserId) {
            System.out.println("You cannot transfer to yourself.");
            return;
        }

        Balance sender = null;
        Balance receiver = null;

        for (Balance balance : balances) {

            if (balance.getUserId() == fromUserId) {
                sender = balance;
            }

            if (balance.getUserId() == toUserId) {
                receiver = balance;
            }
        }

        if (sender == null) {
            System.out.println("Sender account not found.");
            return;
        }

        if (receiver == null) {
            System.out.println("Receiver account not found.");
            return;
        }

        if (sender.getAmount() < amount) {
            System.out.println("Insufficient balance.");
            return;
        }

        sender.setAmount(sender.getAmount() - amount);

        receiver.setAmount(receiver.getAmount() + amount);

        System.out.println("Transfer Successful!");
        System.out.println("Transferred: ₱" + amount);
        System.out.println("Remaining Balance: ₱"
                + sender.getAmount());
    }
}