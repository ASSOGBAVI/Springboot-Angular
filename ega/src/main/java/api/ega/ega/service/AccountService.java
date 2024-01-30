package api.ega.ega.service;

import api.ega.ega.dto.AccountDto;
import api.ega.ega.entity.Account;
import api.ega.ega.entity.Client;
import api.ega.ega.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService{

    @Autowired
    public IAccountRepository accountRepository;

    @Override
    public Account saveAccount(AccountDto accountDto) {
        Account account = convertDtoToEntity(accountDto);
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.deleteById(String.valueOf(id));
    }

    @Override
    public AccountDto showOneAccount(int id) {
        Account account = accountRepository.findById(String.valueOf(id)).get();
        return convertEntityToDto(account);
    }

    @Override
    public List<AccountDto> showAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto convertEntityToDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(Integer.parseInt(account.getAccountNumber()));
        accountDto.setAccountType(account.getAccountType());
        accountDto.setCreationDate(account.getCreationDate());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }


    @Override
    public Account convertDtoToEntity(AccountDto accountDto) {

        Account account = new Account();
        account.setAccountNumber(String.valueOf(accountDto.getAccountNumber()));
        account.setAccountType(accountDto.getAccountType());
        account.setCreationDate(accountDto.getCreationDate());
        account.setBalance(accountDto.getBalance());

        return account;
    }




    // Additional Repository Methods from repo
    public List<AccountDto> findByClient(Client client) {
        List<Account> accounts = accountRepository.findByClient(client);
        return accounts.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }


    public List<AccountDto> findByAccountType(String accountType) {
        List<Account> accounts = accountRepository.findByAccountType(accountType);
        return accounts.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<AccountDto> findByBalance(BigDecimal balance) {
        List<Account> accounts = accountRepository.findByBalance(balance);
        return accounts.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<AccountDto> findByBalanceGreaterThan(BigDecimal balance) {
        List<Account> accounts = accountRepository.findByBalanceGreaterThan(balance);
        return accounts.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<AccountDto> findByBalanceLessThan(BigDecimal balance) {
        List<Account> accounts = accountRepository.findByBalanceLessThan(balance);
        return accounts.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<AccountDto> findByClientAndAccountType(Client client, String accountType) {
       // public List<AccountDto> findByClientAndAccountType(Client client, TypeOfAccount accountType) {

        List<Account> accounts = accountRepository.findByClientAndAccountType(client, accountType);
        return accounts.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public int countAccountsByClientId(int clientId) {
        return accountRepository.countAccountsByClientId(clientId);
    }

    public int countAccountsByTypeAndClientId(int clientId, String accountType) {
        //public int countAccountsByTypeAndClientId(int clientId, TypeOfAccount accountType) {
        return accountRepository.countAccountsByTypeAndClientId(clientId, accountType);
    }

    public BigDecimal totalBalanceByClientId(int clientId) {
        return accountRepository.totalBalanceByClientId(clientId);
    }

    public BigDecimal totalBalanceByClientIdAndAccountType(int clientId, String accountType) {
        //public BigDecimal totalBalanceByClientIdAndAccountType(int clientId, TypeOfAccount accountType) {
        return accountRepository.totalBalanceByClientIdAndAccountType(clientId, accountType);
    }

    public int countAccountsByType(String accountType) {
        //public int countAccountsByType(TypeOfAccount accountType) {

            return accountRepository.countAccountsByType(accountType);
    }

    public BigDecimal totalBalanceByType(String accountType) {
    //public BigDecimal totalBalanceByType(TypeOfAccount accountType) {

            return accountRepository.totalBalanceByType(accountType);
    }

    public BigDecimal totalBalanceOfAllAccounts() {
        return accountRepository.totalBalanceOfAllAccounts();
    }

    public BigDecimal averageBalanceByClientId(int clientId) {
        return accountRepository.averageBalanceByClientId(clientId);
    }

    public BigDecimal maxBalanceByClientId(int clientId) {
        return accountRepository.maxBalanceByClientId(clientId);
    }

    public BigDecimal minBalanceByClientId(int clientId) {
        return accountRepository.minBalanceByClientId(clientId);
    }


}
