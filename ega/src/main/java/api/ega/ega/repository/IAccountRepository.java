package api.ega.ega.repository;

import api.ega.ega.entity.Account;
import api.ega.ega.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String> {

    List<Account> findByClient(Client client);
    List<Account> findAccountsByClientId(int clientId);

    List<Account> findByAccountType(String accountType);
    //List<Account> findByAccountType(TypeOfAccount accountType);

    List<Account> findByBalance(BigDecimal balance);

    List<Account> findByBalanceGreaterThan(BigDecimal balance);

    List<Account> findByBalanceLessThan(BigDecimal balance);

    List<Account> findByClientAndAccountType(Client client, String accountType);
    //List<Account> findByClientAndAccountType(Client client, TypeOfAccount accountType);


    @Query("SELECT COUNT(a) FROM Account a WHERE a.client.id = :clientId")
    int countAccountsByClientId(@Param("clientId") int clientId);

    @Query("SELECT COUNT(a) FROM Account a WHERE a.client.id = :clientId AND a.accountType = :accountType")
    int countAccountsByTypeAndClientId(@Param("clientId") int clientId, @Param("accountType") String accountType);
    //int countAccountsByTypeAndClientId(@Param("clientId") int clientId, @Param("accountType") TypeOfAccount accountType);


    @Query("SELECT SUM(a.balance) FROM Account a WHERE a.client.id = :clientId")
    BigDecimal totalBalanceByClientId(@Param("clientId") int clientId);

    @Query("SELECT SUM(a.balance) FROM Account a WHERE a.client.id = :clientId AND a.accountType = :accountType")
    BigDecimal totalBalanceByClientIdAndAccountType(@Param("clientId") int clientId, @Param("accountType") String accountType);
    //BigDecimal totalBalanceByClientIdAndAccountType(@Param("clientId") int clientId, @Param("accountType") TypeOfAccount accountType);

    @Query("SELECT COUNT(a) FROM Account a WHERE a.accountType = :accountType")
    int countAccountsByType(@Param("accountType") String accountType);
    //int countAccountsByType(@Param("accountType") TypeOfAccount accountType);


    @Query("SELECT SUM(a.balance) FROM Account a WHERE a.accountType = :accountType")
    BigDecimal totalBalanceByType(@Param("accountType") String accountType);
    //BigDecimal totalBalanceByType(@Param("accountType") TypeOfAccount accountType);


    @Query("SELECT SUM(a.balance) FROM Account a")
    BigDecimal totalBalanceOfAllAccounts();

    @Query("SELECT AVG(a.balance) FROM Account a WHERE a.client.id = :clientId")
    BigDecimal averageBalanceByClientId(@Param("clientId") int clientId);

    @Query("SELECT MAX(a.balance) FROM Account a WHERE a.client.id = :clientId")
    BigDecimal maxBalanceByClientId(@Param("clientId") int clientId);

    @Query("SELECT MIN(a.balance) FROM Account a WHERE a.client.id = :clientId")
    BigDecimal minBalanceByClientId(@Param("clientId") int clientId);
}


