package banking.model;

import java.util.Date;

public class Transaction {

    private int id;
    private String type;
    private double amount;
    private Date date;

    public Transaction(int id, String type, double amount, Date date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
