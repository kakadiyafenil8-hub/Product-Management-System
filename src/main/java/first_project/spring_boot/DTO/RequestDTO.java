package first_project.spring_boot.DTO;

public class RequestDTO {
    private long accountNumber;
    private double amount;
    private long fromAccountNumber;
    private long toAcountNumber;
    private double initialBalance;

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public long getToAcountNumber() {
        return toAcountNumber;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFromAccountNumber(long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public void setToAcountNumber(long toAcountNumber) {
        this.toAcountNumber = toAcountNumber;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }
}
