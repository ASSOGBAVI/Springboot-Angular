package api.ega.ega.controller;

import api.ega.ega.dto.ClientDto;
import api.ega.ega.dto.TransactionDto;
import api.ega.ega.entity.Account;
import api.ega.ega.entity.Transaction;
import api.ega.ega.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TransactionController {

    @Autowired
    public TransactionService transactionService;

    @GetMapping("/api/transactions")
    public List<TransactionDto> showAllTransaction(){return transactionService.showAllTransaction();}

    @PostMapping("/api/transactions")
    public Transaction saveTransaction(@RequestBody TransactionDto transactionDto){
        return transactionService.saveTransaction(transactionDto);
    }

    @GetMapping("/api/transactions/{id}")
    public TransactionDto showOneTransaction(@PathVariable int id){
        return transactionService.showOneTransaction(id);
    }

    @DeleteMapping("/api/transactions/{id}")
    public void deleteTransaction(@PathVariable int id){
        transactionService.deleteTransaction(id);
    }

    @PostMapping("/api/transactions/deposit/{accountId}/{amount}")
    public Transaction makeDeposit(@PathVariable String accountId, @PathVariable BigDecimal amount) {
        return transactionService.makeDeposit(accountId, amount);
    }

    @PostMapping("/api/transactions/withdrawal/{accountId}/{amount}")
    public Transaction makeWithdrawal(@PathVariable String accountId, @PathVariable BigDecimal amount) {
        return transactionService.makeWithdrawal(accountId, amount);
    }

    @PostMapping("/api/transactions/transfer/{sourceAccountId}/{recipientAccountId}/{amount}")
    public Transaction makeTransfer(
            @PathVariable String sourceAccountId,
            @PathVariable String recipientAccountId,
            @PathVariable BigDecimal amount
    ) {
        return transactionService.makeTransfer(sourceAccountId, recipientAccountId, amount);
    }
/*
    @GetMapping("/api/transactions/findByRecipientAccountId")
    public List<TransactionDto> findByRecipientAccountId(@RequestParam String accountId) {
        return transactionService.findByRecipientAccountId(accountId);
    }

    @GetMapping("/api/transactions/findBySourceAccountId")
    public List<TransactionDto> findBySourceAccountId(@RequestParam String accountId) {
        return transactionService.findBySourceAccountId(accountId);
    }

    @GetMapping("/api/transactions/findByTransactionDateBetween")
    public List<TransactionDto> findByTransactionDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return transactionService.findByTransactionDateBetween(startDate, endDate);
    }

    @GetMapping("/api/transactions/findByTransactionDateAfter")
    public List<TransactionDto> findByTransactionDateAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate transactionDate
    ) {
        return transactionService.findByTransactionDateAfter(transactionDate);
    }

    @GetMapping("/api/transactions/findByTransactionDateBefore")
    public List<TransactionDto> findByTransactionDateBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate transactionDate
    ) {
        return transactionService.findByTransactionDateBefore(transactionDate);
    }

    @GetMapping("/api/transactions/findByAmount")
    public List<TransactionDto> findByAmount(@RequestParam BigDecimal amount) {
        return transactionService.findByAmount(amount);
    }

    @GetMapping("/api/transactions/findByAmountGreaterThan")
    public List<TransactionDto> findByAmountGreaterThan(@RequestParam BigDecimal amount) {
        return transactionService.findByAmountGreaterThan(amount);
    }

    @GetMapping("/api/transactions/findByAmountLessThan")
    public List<TransactionDto> findByAmountLessThan(@RequestParam BigDecimal amount) {
        return transactionService.findByAmountLessThan(amount);
    }

    @GetMapping("/api/transactions/findByAmountBetween")
    public List<TransactionDto> findByAmountBetween(
            @RequestParam BigDecimal minAmount,
            @RequestParam BigDecimal maxAmount
    ) {
        return transactionService.findByAmountBetween(minAmount, maxAmount);
    }


    //complex//@meisekii

*/
/*    @GetMapping("/api/transactions/findAllBySourceAccountIdOrRecipientAccountId")
    public List<TransactionDto> findAllBySourceAccountIdOrRecipientAccountId(@RequestParam String sourceAccountId, @RequestParam String recipientAccountId) {
        Account sourceAccount = new Account();
        sourceAccount.setAccountNumber(sourceAccountId);

        Account recipientAccount = new Account();
        recipientAccount.setAccountNumber(recipientAccountId);

        return transactionService.findAllBySourceAccountIdOrRecipientAccountId(sourceAccountId, recipientAccountId);
    }*//*


    @GetMapping("/api/transactions/findAllBySourceAccountIdAndRecipientAccountId")
    public List<TransactionDto> findAllBySourceAccountIdAndRecipientAccountId(
            @RequestParam String sourceAccountId,
            @RequestParam String recipientAccountId
    ) {
        Account sourceAccount = new Account();
        sourceAccount.setAccountNumber(sourceAccountId);

        Account recipientAccount = new Account();
        recipientAccount.setAccountNumber(recipientAccountId);

        return transactionService.findAllBySourceAccountIdAndRecipientAccountId(sourceAccount, recipientAccount);
    }
*/

/*    @GetMapping("/api/transactions/totalAmountByAccountId")
    public BigDecimal totalAmountByAccountId(@RequestParam String accountId) {
        Account account = new Account();
        account.setAccountNumber(accountId);
        return transactionService.totalAmountByAccountId(account);
    }*/





}
