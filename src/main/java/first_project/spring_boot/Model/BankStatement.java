package first_project.spring_boot.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_statement")
public class BankStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account_no")
    private long accountNo;
    private String transaction_type;

    private double balance_before;
    private double balance_after;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;

    // ===== Getters =====
    public int getId() {
        return id;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public double getBalance_before() {
        return balance_before;
    }

    public double getBalance_after() {
        return balance_after;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    // ===== Setters =====
    public void setId(int id) {
        this.id = id;
    }

    public void setAccountNo(long account_no) {   // ← changed to long
        this.accountNo = account_no;
    }

    public void setBalance_before(double balance_before) {
        this.balance_before = balance_before;
    }

    public void setBalance_after(double balance_after) {
        this.balance_after = balance_after;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}