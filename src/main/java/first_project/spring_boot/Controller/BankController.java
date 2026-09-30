package first_project.spring_boot.Controller;
import org.springframework.http.ResponseEntity;
import first_project.spring_boot.DTO.RequestDTO;
import first_project.spring_boot.DTO.ResponseDTO;
import first_project.spring_boot.Model.BankAccount;
import first_project.spring_boot.Model.BankStatement;
import first_project.spring_boot.Model.TransferRequest;
import first_project.spring_boot.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account_details")
public class BankController {

@Autowired
BankService bankService;

// CREATE ACCOUNT
@PostMapping("save_account")
public ResponseDTO saveAccount(@RequestBody RequestDTO request) {

    return bankService.saveAccount(request);
}


// DEPOSIT
@PostMapping("deposit_money")
public ResponseDTO depositMoney(@RequestBody RequestDTO request) {

    return bankService.depositMoney(request);
}


// WITHDRAW
@PostMapping("withdraw_money")
public ResponseDTO withdrawMoney(@RequestBody RequestDTO request) {

    return bankService.withdrawMoney(request);
}


// GET BALANCE
@GetMapping("get_balance")
public ResponseDTO getBalance(@RequestBody RequestDTO request) {
    return bankService.getBalance(request);
}


// GET STATEMENT
@GetMapping("get_statement")
public List<ResponseDTO> getStatement(@RequestBody RequestDTO request) {

    return bankService.getStatement(request);
}


// BANK TRANSFER
@PostMapping("bank_transfer")
public ResponseDTO bankTransfer(@RequestBody RequestDTO request) {

    return bankService.bankTransfer(request);
}

}