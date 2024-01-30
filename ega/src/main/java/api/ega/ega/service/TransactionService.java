package api.ega.ega.service;

import api.ega.ega.dto.TransactionDto;
import api.ega.ega.entity.Account;
import api.ega.ega.entity.Client;
import api.ega.ega.entity.Transaction;
import api.ega.ega.repository.IAccountRepository;
import api.ega.ega.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService{

    @Autowired
    public ITransactionRepository transactionRepository;

    @Autowired
    public IAccountRepository accountRepository;


    @Override
    public Transaction saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = convertDtoToEntity(transactionDto);
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(int id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public TransactionDto showOneTransaction(int id) {
        Transaction transaction = transactionRepository.findById(id).get();
        return convertEntityToDto(transaction);
    }

    @Override
    public List<TransactionDto> showAllTransaction() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    @Override
    public TransactionDto convertEntityToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setSourceAccountId(transaction.getSourceAccount().getAccountNumber());
        transactionDto.setRecipientAccountId(transaction.getRecipientAccount().getAccountNumber());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactionDate(transaction.getTransactionDate());
        return transactionDto;
    }

    @Override
    public Transaction convertDtoToEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        Account sourceAccount = new Account();
        sourceAccount.setAccountNumber(String.valueOf(transactionDto.getSourceAccountId()));
        Account recipientAccount = new Account();
        recipientAccount.setAccountNumber(String.valueOf(transactionDto.getRecipientAccountId()));
        transaction.setSourceAccount(sourceAccount);
        transaction.setRecipientAccount(recipientAccount);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());
        return transaction;
    }



    @Override
    public Transaction makeDeposit(String accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found"));

        BigDecimal updatedBalance = account.getBalance().add(amount);
        account.setBalance(updatedBalance);

        Transaction depositTransaction = new Transaction();
        depositTransaction.setSourceAccount(account);
        depositTransaction.setRecipientAccount(account); // Deposit into the same account
        depositTransaction.setAmount(amount);
        depositTransaction.setTransactionDate(LocalDateTime.now());

        accountRepository.save(account);
        return transactionRepository.save(depositTransaction);
    }

    @Override
    public Transaction makeWithdrawal(String accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found"));

        BigDecimal currentBalance = account.getBalance();
        if (currentBalance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        BigDecimal updatedBalance = currentBalance.subtract(amount);
        account.setBalance(updatedBalance);

        Transaction withdrawalTransaction = new Transaction();
        withdrawalTransaction.setSourceAccount(account);
        withdrawalTransaction.setRecipientAccount(account); // Withdrawal from the same account
        withdrawalTransaction.setAmount(amount);
        withdrawalTransaction.setTransactionDate(LocalDateTime.now());

        accountRepository.save(account);
        return transactionRepository.save(withdrawalTransaction);
    }

    @Override
    public Transaction makeTransfer(String sourceAccountId, String recipientAccountId, BigDecimal amount) {
        Account sourceAccount = accountRepository.findById(sourceAccountId).orElseThrow(() -> new IllegalArgumentException("Source account not found"));
        Account recipientAccount = accountRepository.findById(recipientAccountId).orElseThrow(() -> new IllegalArgumentException("Recipient account not found"));

        BigDecimal sourceBalance = sourceAccount.getBalance();
        if (sourceBalance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds for transfer");
        }

        BigDecimal updatedSourceBalance = sourceBalance.subtract(amount);
        sourceAccount.setBalance(updatedSourceBalance);
        accountRepository.save(sourceAccount);

        BigDecimal recipientBalance = recipientAccount.getBalance().add(amount);
        recipientAccount.setBalance(recipientBalance);
        accountRepository.save(recipientAccount);

        Transaction transferFromSource = new Transaction();
        transferFromSource.setSourceAccount(sourceAccount);
        transferFromSource.setRecipientAccount(recipientAccount);
        transferFromSource.setAmount(amount);
        transferFromSource.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transferFromSource);

        Transaction transferToRecipient = new Transaction();
        transferToRecipient.setSourceAccount(sourceAccount);
        transferToRecipient.setRecipientAccount(recipientAccount);
        transferToRecipient.setAmount(amount);
        transferToRecipient.setTransactionDate(LocalDateTime.now());
        return transactionRepository.save(transferToRecipient);
    }


    // Additional Repository Methods from repo

    public List<TransactionDto> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = transactionRepository.findByTransactionDateBetween(startDate, endDate);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findByTransactionDateAfter(LocalDate transactionDate) {
        List<Transaction> transactions = transactionRepository.findBytransactionDateAfter(transactionDate);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findByTransactionDateBefore(LocalDate transactionDate) {
        List<Transaction> transactions = transactionRepository.findBytransactionDateBefore(transactionDate);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }


    public List<TransactionDto> findByAmount(BigDecimal amount) {
        List<Transaction> transactions = transactionRepository.findByAmount(amount);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findByAmountGreaterThan(BigDecimal amount) {
        List<Transaction> transactions = transactionRepository.findByAmountGreaterThan(amount);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findByAmountLessThan(BigDecimal amount) {
        List<Transaction> transactions = transactionRepository.findByAmountLessThan(amount);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount) {
        List<Transaction> transactions = transactionRepository.findByAmountBetween(minAmount, maxAmount);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findAllBySourceAccountIdAndRecipientAccountId(Account sourceAccount, Account recipientAccount) {
        List<Transaction> transactions = transactionRepository.findAllBySourceAccountIdAndRecipientAccountId(sourceAccount, recipientAccount);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }


    public BigDecimal totalAmountByAccountId(Account accountId) {
        return transactionRepository.totalAmountByAccountId(String.valueOf(accountId));
    }

   // @meisekii_error
/*
    public List<TransactionDto> findBySourceAccountId(String sourceAccount) {
        List<Transaction> transactions = transactionRepository.findBySourceAccountId(sourceAccount);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findByRecipientAccountId(String recipientAccount) {
        List<Transaction> transactions = transactionRepository.findByRecipientAccountId(recipientAccount);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
*/


/*
    //@meisekii_remind
    public List<TransactionDto> findAllBySourceAccountId_ClientOrRecipientAccountId_Client(Client client) {
        List<Transaction> transactions = transactionRepository.findAllBySourceAccountId_ClientOrRecipientAccountId_Client(client);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

*/
/*
   public List<TransactionDto> findAllBySourceAccountIdOrRecipientAccountId (String sourceAccount, String recipientAccount) {
        List<Transaction> transactions = transactionRepository.findAllBySourceAccountId_ClientOrRecipientAccountId_Client(sourceAccount, recipientAccount);
        return transactions.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
*/





}
