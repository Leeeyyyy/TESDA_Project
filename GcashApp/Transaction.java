package GcashApp;

import java.time.LocalDateTime;

public class Transaction {

    private int id;
    private double amount;
    private String name;
    private int accountId;
    private LocalDateTime date;
    private int transferToId;
    private int transferFromId;

    public Transaction(int id,
                       double amount,
                       String name,
                       int accountId,
                       LocalDateTime date,
                       int transferToId,
                       int transferFromId) {

        this.id = id;
        this.amount = amount;
        this.name = name;
        this.accountId = accountId;
        this.date = date;
        this.transferToId = transferToId;
        this.transferFromId = transferFromId;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public int getAccountId() {
        return accountId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getTransferToId() {
        return transferToId;
    }

    public int getTransferFromId() {
        return transferFromId;
    }
}