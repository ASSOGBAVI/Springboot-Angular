package api.ega.ega.service;

import api.ega.ega.dto.ClientDto;
import api.ega.ega.dto.TransactionDto;
import api.ega.ega.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {
    public Transaction saveTransaction(TransactionDto transactionDto);
    public void deleteTransaction(int id);
    public TransactionDto showOneTransaction(int id);
    public List<TransactionDto> showAllTransaction();
    public TransactionDto convertEntityToDto(Transaction transaction);
    public Transaction convertDtoToEntity(TransactionDto transactionDto);
    public Transaction makeDeposit(String accountId, BigDecimal amount);
    public Transaction makeWithdrawal(String accountId, BigDecimal amount);
    public Transaction makeTransfer(String sourceAccountId, String recipientAccountId, BigDecimal amount);

}
