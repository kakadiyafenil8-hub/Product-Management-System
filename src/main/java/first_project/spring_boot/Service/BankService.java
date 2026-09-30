package first_project.spring_boot.Service;

import first_project.spring_boot.DTO.RequestDTO;
import first_project.spring_boot.DTO.ResponseDTO;
import first_project.spring_boot.Model.BankAccount;
import first_project.spring_boot.Model.BankStatement;
import first_project.spring_boot.Model.BankTransferDetails;
import first_project.spring_boot.Repository.BankRepository;
import first_project.spring_boot.Repository.BankStatementRepository;
import first_project.spring_boot.Repository.BankTransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankService {
    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankStatementRepository bankstatementrepository;

    @Autowired
    BankTransferRepository bankTransferRepository;

    public ResponseDTO saveAccount(RequestDTO request) {

        if (request.getInitialBalance() < 0) {
            throw new RuntimeException(
                    "Initial balance cannot be negative");
        }

        long accountNumber;

        do {

            accountNumber = 1000000000L
                    + (long) (Math.random() * 9000000000L);

        } while (
                bankRepository.existsByAccountNumber(accountNumber)
        );


        BankAccount account = new BankAccount();

        account.setAccountNumber(accountNumber);

        account.setBalance(
                request.getInitialBalance()
        );


        LocalDateTime now = LocalDateTime.now();

        account.setCreatedAt(now);

        account.setUpdatedAt(now);


        bankRepository.save(account);


        // Create Response DTO

        ResponseDTO response = new ResponseDTO();

        response.setAccountNumber(
                account.getAccountNumber()
        );

        response.setBalance(
                account.getBalance()
        );


        return response;
    }


    // =====================================================
    // DEPOSIT
    // =====================================================

    public ResponseDTO depositMoney(RequestDTO request) {

        if (request.getAmount() <= 0) {

            throw new RuntimeException(
                    "Deposit amount must be greater than 0");
        }


        BankAccount account =
                bankRepository
                        .findByAccountNumber(
                                request.getAccountNumber()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Account not found"));


        double balanceBefore =
                account.getBalance();


        double balanceAfter =
                balanceBefore + request.getAmount();


        account.setBalance(balanceAfter);

        account.setUpdatedAt(
                LocalDateTime.now()
        );


        bankRepository.save(account);


        // Create Statement

        BankStatement statement =
                new BankStatement();

        statement.setAccountNo(
                account.getAccountNumber()
        );

        statement.setBalance_before(
                balanceBefore
        );

        statement.setBalance_after(
                balanceAfter
        );

        statement.setTransaction_type(
                "Deposit"
        );


        LocalDateTime now =
                LocalDateTime.now();

        statement.setCreated_at(now);

        statement.setUpdated_at(now);


        bankstatementrepository.save(statement);


        // Response DTO

        ResponseDTO response =
                new ResponseDTO();

        response.setAccountNumber(
                account.getAccountNumber()
        );

        response.setBalance(
                account.getBalance()
        );


        return response;
    }


    // =====================================================
    // WITHDRAW
    // =====================================================

    public ResponseDTO withdrawMoney(RequestDTO request) {

        if (request.getAmount() <= 0) {

            throw new RuntimeException(
                    "Withdrawal amount must be greater than 0");
        }


        BankAccount account =
                bankRepository
                        .findByAccountNumber(
                                request.getAccountNumber()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Account not found"));


        double balanceBefore =
                account.getBalance();


        if (request.getAmount() > balanceBefore) {

            throw new RuntimeException(
                    "Insufficient balance");
        }


        double balanceAfter =
                balanceBefore - request.getAmount();


        account.setBalance(balanceAfter);

        account.setUpdatedAt(
                LocalDateTime.now()
        );


        bankRepository.save(account);


        // Create Statement

        BankStatement statement =
                new BankStatement();

        statement.setAccountNo(
                account.getAccountNumber()
        );

        statement.setBalance_before(
                balanceBefore
        );

        statement.setBalance_after(
                balanceAfter
        );

        statement.setTransaction_type(
                "Withdraw"
        );


        LocalDateTime now =
                LocalDateTime.now();

        statement.setCreated_at(now);

        statement.setUpdated_at(now);


        bankstatementrepository.save(statement);


        // Response DTO

        ResponseDTO response =
                new ResponseDTO();

        response.setAccountNumber(
                account.getAccountNumber()
        );

        response.setBalance(
                account.getBalance()
        );


        return response;
    }


    // =====================================================
    // GET BALANCE
    // =====================================================

    public ResponseDTO getBalance(RequestDTO request) {

        BankAccount account =
                bankRepository
                        .findByAccountNumber(
                                request.getAccountNumber()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Account not found"));


        ResponseDTO response =
                new ResponseDTO();

        response.setAccountNumber(
                account.getAccountNumber()
        );

        response.setBalance(
                account.getBalance()
        );

        response.setMsg("Balance Fetched Successfully");


        return response;
    }


    // =====================================================
    // GET STATEMENT
    // =====================================================

    public List<ResponseDTO> getStatement(
            RequestDTO request) {

        bankRepository
                .findByAccountNumber(
                        request.getAccountNumber()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found"));


        List<BankStatement> statements =
                bankstatementrepository
                        .findByAccountNo(
                                request.getAccountNumber()
                        );


        return statements.stream()
                .map(statement -> {

                    ResponseDTO response =
                            new ResponseDTO();

                    response.setAccountNumber(
                            statement.getAccountNo()
                    );

                    response.setTransactionType(
                            statement.getTransaction_type()
                    );

                    response.setBalanceBefore(
                            statement.getBalance_before()
                    );

                    response.setBalanceAfter(
                            statement.getBalance_after()
                    );

                    response.setCreatedAt(
                            statement.getCreated_at()
                    );

                    return response;

                })
                .toList();
    }


    // =====================================================
    // BANK TRANSFER
    // =====================================================

    @Transactional
    public ResponseDTO bankTransfer(
            RequestDTO request) {


        long fromAccountNumber =
                request.getFromAccountNumber();

        long toAccountNumber =
                request.getToAcountNumber();

        double amount =
                request.getAmount();


        // Check amount

        if (amount <= 0) {

            throw new RuntimeException(
                    "Amount must be greater than 0");
        }


        // Check same account

        if (fromAccountNumber == toAccountNumber) {

            throw new RuntimeException(
                    "Cannot transfer money to the same account");
        }


        // Find sender

        BankAccount sender =
                bankRepository
                        .findByAccountNumber(
                                fromAccountNumber
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sender account not found"));


        // Find receiver

        BankAccount receiver =
                bankRepository
                        .findByAccountNumber(
                                toAccountNumber
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Receiver account not found"));


        // Check balance

        if (sender.getBalance() < amount) {

            throw new RuntimeException(
                    "Insufficient balance");
        }


        double senderBefore =
                sender.getBalance();


        // Deduct sender

        sender.setBalance(
                sender.getBalance() - amount
        );


        // Add receiver

        receiver.setBalance(
                receiver.getBalance() + amount
        );


        LocalDateTime now =
                LocalDateTime.now();


        sender.setUpdatedAt(now);

        receiver.setUpdatedAt(now);


        // Save accounts

        bankRepository.save(sender);

        bankRepository.save(receiver);


        // Create transfer record

        BankTransferDetails transfer =
                new BankTransferDetails();


        transfer.setFromAccountNumber(
                fromAccountNumber
        );

        transfer.setToAccountNumber(
                toAccountNumber
        );

        transfer.setAmount(amount);


        transfer.setBeforeBalance(
                senderBefore
        );

        transfer.setAfterBalance(
                sender.getBalance()
        );


        transfer.setCreatedAt(now);

        transfer.setUpdatedAt(now);


        bankTransferRepository.save(transfer);


        // Response DTO

        ResponseDTO response =
                new ResponseDTO();


        response.setFromAccountNumber(
                fromAccountNumber
        );

        response.setToAccountNumber(
                toAccountNumber
        );

        response.setAmount(amount);

        response.setBalance(
                sender.getBalance()
        );


        return response;
    }
}
