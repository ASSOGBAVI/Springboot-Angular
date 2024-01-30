package api.ega.ega.repository;

import api.ega.ega.dto.ClientDto;
import api.ega.ega.entity.Account;
import api.ega.ega.entity.Client;
import api.ega.ega.enums.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {


    //methods with JPA Convention
    List<Client> findByClientLastName(String clientLastName);

    List<Client> findByPhone(String phone);

    List<Client> findBySexe(String sexe);
    List<Client> findByAddress(String address);
    List<Client> findByNationality(Pays nationality);

    List<Client> findByClientFirstNameAndClientLastName(String firstName, String lastName);
    List<Client> findByBirthDateAfter(LocalDate birthDate);
    List<Client> findByBirthDateBefore(LocalDate birthDate);
    List<Client> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);



    @Query("SELECT c FROM Client c WHERE c.clientLastName LIKE ?1%")
    List<Client> findByClientLastNameStartedByChar(String clientLastName);

    @Query("SELECT c FROM Client c WHERE c.birthDate BETWEEN ?1 AND ?2")
    List<Client> findByBirthDate(LocalDate d1, LocalDate d2);

    @Query("SELECT c FROM Client c WHERE c.accounts IS EMPTY")
    List<Client> findClientsWithoutAccounts();

    @Query("SELECT c FROM Client c JOIN c.accounts a")
    List<Client> findClientsWithAccounts();

    @Query("SELECT a FROM Account a WHERE a.client.id = :clientId")
    List<Account> findAccountsByClientId(@Param("clientId") int clientId);

    @Query("SELECT COUNT(a) FROM Account a WHERE a.client.id = :clientId")
    int getNumberOfAccountsByClientId(@Param("clientId") int clientId);

    @Query("SELECT COUNT(a) FROM Account a WHERE a.client.clientLastName = :clientLastName")
    int getNumberOfAccountsByClientLastName(@Param("clientLastName") String clientLastName);

    @Query("SELECT c FROM Client c JOIN c.accounts a WHERE a.accountNumber = :accountNumber")
    Client findClientByAccountNumber(@Param("accountNumber") String accountNumber);

    @Query("SELECT c FROM Client c JOIN c.accounts a WHERE a.accountType = :accountType")
    List<Client> findByAccountType(@Param("accountType") String accountType);
    //List<Client> findByAccountType(@Param("accountType") String accountType);


    @Query("SELECT COUNT(a) FROM Client c JOIN c.accounts a WHERE c.id = :clientId AND a.accountType = :accountType")
    int countAccountByTypeForClient(@Param("clientId") int clientId, @Param("accountType") String accountType);
    //int countAccountByTypeForClient(@Param("clientId") int clientId, @Param("accountType") TypeOfAccount accountType);





}