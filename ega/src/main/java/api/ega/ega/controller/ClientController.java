package api.ega.ega.controller;

import api.ega.ega.dto.ClientDto;
import api.ega.ega.entity.Client;
import api.ega.ega.enums.Pays;
import api.ega.ega.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClientController {

    @Autowired
    public ClientService clientService;

    @GetMapping("/api/clients")
    public List<ClientDto> showAllClient(){return clientService.showAllClient();}


    @PostMapping("/api/clients")
    public Client saveClient(@RequestBody ClientDto clientDto){
        return clientService.saveClient(clientDto);
    }

    @GetMapping("/api/clients/{id}")
    public ClientDto showOneClient(@PathVariable int id){
        return clientService.showOneClient(id);
    }

    @DeleteMapping("/api/clients/{id}")
    public void deleteClient(@PathVariable int id){
        clientService.deleteClient(id);
    }

    @GetMapping("/api/clients/findByClientLastName/{clientLastName}")
    public List<ClientDto> findByClientLastName(@PathVariable String clientLastName) {
        return clientService.findByClientLastName(clientLastName);
    }

    @GetMapping("/api/clients/findByPhone/{phone}")
    public List<ClientDto> findByPhone(@PathVariable String phone) {
        return clientService.findByPhone(phone);
    }

    @GetMapping("/api/clients/findBySexe/{sexe}")
    public List<ClientDto> findBySexe(@PathVariable String sexe) {
        return clientService.findBySexe(sexe);
    }

    @GetMapping("/api/clients/findByAddress/{address}")
    public List<ClientDto> findByAddress(@PathVariable String address) {
        return clientService.findByAddress(address);
    }

    @GetMapping("/api/clients/findByNationality/{nationality}")
    public List<ClientDto> findByNationality(@PathVariable Pays nationality) {
        return clientService.findByNationality(nationality);
    }

    @GetMapping("/api/clients/findByClientFirstNameAndClientLastName/{firstName}/{lastName}")
    public List<ClientDto> findByClientFirstNameAndClientLastName(@PathVariable String firstName, @PathVariable String lastName) {
        return clientService.findByClientFirstNameAndClientLastName(firstName, lastName);
    }

    @GetMapping("/api/clients/findByBirthDateAfter/{birthDate}")
    public List<ClientDto> findByBirthDateAfter(@PathVariable LocalDate birthDate) {
        return clientService.findByBirthDateAfter(birthDate);
    }

    @GetMapping("/api/clients/findByBirthDateBefore/{birthDate}")
    public List<ClientDto> findByBirthDateBefore(@PathVariable LocalDate birthDate) {
        return clientService.findByBirthDateBefore(birthDate);
    }

    @GetMapping("/api/clients/findByBirthDateBetween/{startDate}/{endDate}")
    public List<ClientDto> findByBirthDateBetween(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return clientService.findByBirthDateBetween(startDate, endDate);
    }

    @GetMapping("/api/clients/findByClientLastNameStartedByChar/{clientLastName}")
    public List<ClientDto> findByClientLastNameStartedByChar(@PathVariable String clientLastName) {
        return clientService.findByClientLastNameStartedByChar(clientLastName);
    }

    @GetMapping("/api/clients/findByBirthDate/{d1}/{d2}")
    public List<ClientDto> findByBirthDate(@PathVariable LocalDate d1, @PathVariable LocalDate d2) {
        return clientService.findByBirthDate(d1, d2);
    }

    @GetMapping("/api/clients/findClientsWithAccounts")
    public List<ClientDto> findClientsWithAccounts() {
        return clientService.findClientsWithAccounts();
    }

    @GetMapping("/api/clients/findClientsWithoutAccounts")
    public List<ClientDto> findClientsWithoutAccounts() {
        return clientService.findClientsWithoutAccounts();
    }

    @GetMapping("/api/clients/findByAccountType/{accountType}")
    public List<ClientDto> findByAccountType(@PathVariable String accountType) {
        //public List<ClientDto> findByAccountType(@PathVariable TypeOfAccount accountType) {

            return clientService.findByAccountType(accountType);
    }



    @GetMapping("/api/clients/getNumberOfAccountsByClientId/{clientId}")
    public int getNumberOfAccountsByClientId(@PathVariable int clientId) {
        return clientService.getNumberOfAccountsByClientId(clientId);
    }

    @GetMapping("/api/clients/getNumberOfAccountsByClientLastName/{clientLastName}")
    public int getNumberOfAccountsByClientLastName(@PathVariable String clientLastName) {
        return clientService.getNumberOfAccountsByClientLastName(clientLastName);
    }

    @GetMapping("/api/clients/findClientByAccountNumber/{accountNumber}")
    public ClientDto findClientByAccountNumber(@PathVariable String accountNumber) {
        return clientService.findClientByAccountNumber(accountNumber);
    }

    @GetMapping("/api/clients/countAccountByTypeForClient/{clientId}/{accountType}")
    public int countAccountByTypeForClient(@PathVariable int clientId, @PathVariable String accountType) {
        //public int countAccountByTypeForClient(@PathVariable int clientId, @PathVariable TypeOfAccount accountType) {

            return clientService.countAccountByTypeForClient(clientId, accountType);
    }

}
