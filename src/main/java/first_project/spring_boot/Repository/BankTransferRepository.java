package first_project.spring_boot.Repository;

import first_project.spring_boot.Model.BankTransferDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransferRepository extends JpaRepository<BankTransferDetails, Integer> {

}
