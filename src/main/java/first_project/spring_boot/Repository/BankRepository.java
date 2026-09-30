package first_project.spring_boot.Repository;

import first_project.spring_boot.Model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<BankAccount, Integer> {

    boolean existsByAccountNumber(long accountNumber);

    Optional<BankAccount> findByAccountNumber(long accountNumber);
}