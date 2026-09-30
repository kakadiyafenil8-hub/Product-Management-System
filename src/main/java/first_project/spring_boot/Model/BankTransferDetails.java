package first_project.spring_boot.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "account_transfer")
public class BankTransferDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "from_account_number", nullable = false)
    private long fromAccountNumber;

    @Column(name = "to_account_number", nullable = false)
    private long toAccountNumber;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "before_balance", nullable = false)
    private Double beforeBalance;

    @Column(name = "after_balance", nullable = false)
    private Double afterBalance;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Getters

    public int getId() {
        return id;
    }

    public long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public long getToAccountNumber() {
        return toAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getBeforeBalance() {
        return beforeBalance;
    }

    public Double getAfterBalance() {
        return afterBalance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setFromAccountNumber(long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public void setToAccountNumber(long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public void setBeforeBalance(Double beforeBalance) {
        this.beforeBalance = beforeBalance;
    }

    public void setAfterBalance(Double afterBalance) {
        this.afterBalance = afterBalance;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}