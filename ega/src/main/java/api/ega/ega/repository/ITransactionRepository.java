package api.ega.ega.repository;

import api.ega.ega.entity.Account;
import api.ega.ega.entity.Client;
import api.ega.ega.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);

    List<Transaction> findBytransactionDateAfter(LocalDate transactionDate);
    List<Transaction> findBytransactionDateBefore(LocalDate transactionDate);


    List<Transaction> findByAmount(BigDecimal amount);

    List<Transaction> findByAmountGreaterThan(BigDecimal amount);

    List<Transaction> findByAmountLessThan(BigDecimal amount);

    List<Transaction> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);


    List<Transaction> findAllBySourceAccountIdAndRecipientAccountId(Account sourceAccount, Account recipientAccount);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.sourceAccount.accountNumber = :accountId OR t.recipientAccount.accountNumber = :accountId")
    BigDecimal totalAmountByAccountId(@Param("accountId") String accountId);


    // @meisekii_error
/*
    List<Transaction> findBySourceAccountId(String sourceAccount);

    List<Transaction> findByRecipientAccountId(String recipientAccount);
*/



/*
    List<Transaction> findAllBySourceAccountId_ClientOrRecipientAccountId_Client(Client client);

*/
//    List<Transaction> findAllBySourceAccountIdOrRecipientAccountId(String sourceAccount, String recipientAccount);




}
