package banking.model;


public class CheckingAccount extends Account {

    private double fee;

    public CheckingAccount(String accountNumber, double fee) {
        super(accountNumber);
        this.fee = fee;
    }

    @Override
    public void withdraw(double amount) {
        double total = amount + fee;
        if (total <= balance) {
            balance -= total;
        }
    }


    
}
