package api.ega.ega.controller;

import api.ega.ega.dto.AccountDto;
import api.ega.ega.entity.Account;
import api.ega.ega.entity.Client;
import api.ega.ega.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/api/accounts")
    public List<AccountDto> showAllAccount(){
        return accountService.showAllAccount();
    }

    @PostMapping("/api/accounts")
    public Account saveAccount(@RequestBody AccountDto accountDto){
        return accountService.saveAccount(accountDto);
    }

    @GetMapping("/api/accounts/{id}")
    public AccountDto showOneAccount(@PathVariable int id){
        return accountService.showOneAccount(id);
    }

    @DeleteMapping("/api/accounts/{id}")
    public void deleteAccount(@PathVariable int id){
        accountService.deleteAccount(id);
    }

    @GetMapping("/api/accounts/countByClientId/{clientId}")
    public int countAccountsByClientId(@PathVariable int clientId) {
        return accountService.countAccountsByClientId(clientId);
    }

    @GetMapping("/api/accounts/countByTypeAndClientId/{clientId}/{accountType}")
    public int countAccountsByTypeAndClientId(@PathVariable int clientId, @PathVariable String accountType) {
    //public int countAccountsByTypeAndClientId(@PathVariable int clientId, @PathVariable TypeOfAccount accountType) {

            return accountService.countAccountsByTypeAndClientId(clientId, accountType);
    }

    @GetMapping("/api/accounts/byClient/{clientId}")
    public List<AccountDto> findByClient(@PathVariable int clientId) {
        Client client = new Client(); // Replace this with your logic to fetch the client based on clientId
        return accountService.findByClient(client);
    }

    @GetMapping("/api/accounts/byAccountType/{accountType}")
    public List<AccountDto> findByAccountType(@PathVariable String accountType) {
    //public List<AccountDto> findByAccountType(@PathVariable String accountType) {

            return accountService.findByAccountType(accountType);
    }

    @GetMapping("/api/accounts/byBalance/{balance}")
    public List<AccountDto> findByBalance(@PathVariable BigDecimal balance) {
        return accountService.findByBalance(balance);
    }

    @GetMapping("/api/accounts/byBalanceGreaterThan/{balance}")
    public List<AccountDto> findByBalanceGreaterThan(@PathVariable BigDecimal balance) {
        return accountService.findByBalanceGreaterThan(balance);
    }

    @GetMapping("/api/accounts/byBalanceLessThan/{balance}")
    public List<AccountDto> findByBalanceLessThan(@PathVariable BigDecimal balance) {
        return accountService.findByBalanceLessThan(balance);
    }

    @GetMapping("/api/accounts/byClientAndAccountType/{clientId}/{accountType}")
    public List<AccountDto> findByClientAndAccountType(@PathVariable int clientId, @PathVariable String accountType) {
        //public List<AccountDto> findByClientAndAccountType(@PathVariable int clientId, @PathVariable TypeOfAccount accountType) {

            Client client = new Client(); // Replace this with your logic to fetch the client based on clientId
        return accountService.findByClientAndAccountType(client, accountType);
    }

    // Additional methods for count and balance retrieval

    @GetMapping("/api/accounts/totalBalanceByClientId/{clientId}")
    public BigDecimal totalBalanceByClientId(@PathVariable int clientId) {
        return accountService.totalBalanceByClientId(clientId);
    }

    @GetMapping("/api/accounts/totalBalanceByClientIdAndAccountType/{clientId}/{accountType}")
    public BigDecimal totalBalanceByClientIdAndAccountType(@PathVariable int clientId, @PathVariable String accountType) {
        //public BigDecimal totalBalanceByClientIdAndAccountType(@PathVariable int clientId, @PathVariable TypeOfAccount accountType) {

            return accountService.totalBalanceByClientIdAndAccountType(clientId, accountType);
    }

    @GetMapping("/api/accounts/countByType/{accountType}")
    public int countAccountsByType(@PathVariable String accountType) {
        //public int countAccountsByType(@PathVariable TypeOfAccount accountType) {
        return accountService.countAccountsByType(accountType);
    }

    @GetMapping("/api/accounts/totalBalanceByType/{accountType}")
    public BigDecimal totalBalanceByType(@PathVariable String accountType) {
            //public BigDecimal totalBalanceByType(@PathVariable TypeOfAccount accountType) {


        return accountService.totalBalanceByType(accountType);
    }

    @GetMapping("/api/accounts/totalBalanceOfAllAccounts")
    public BigDecimal totalBalanceOfAllAccounts() {
        return accountService.totalBalanceOfAllAccounts();
    }

    @GetMapping("/api/accounts/averageBalanceByClientId/{clientId}")
    public BigDecimal averageBalanceByClientId(@PathVariable int clientId) {
        return accountService.averageBalanceByClientId(clientId);
    }

    @GetMapping("/api/accounts/maxBalanceByClientId/{clientId}")
    public BigDecimal maxBalanceByClientId(@PathVariable int clientId) {
        return accountService.maxBalanceByClientId(clientId);
    }

    @GetMapping("/api/accounts/minBalanceByClientId/{clientId}")
    public BigDecimal minBalanceByClientId(@PathVariable int clientId) {
        return accountService.minBalanceByClientId(clientId);
    }
}
