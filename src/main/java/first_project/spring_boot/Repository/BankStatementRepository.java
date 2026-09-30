package first_project.spring_boot.Repository;

import first_project.spring_boot.Model.BankStatement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankStatementRepository extends JpaRepository<BankStatement, Integer> {
    List<BankStatement> findByAccountNo(long accountNo);

}