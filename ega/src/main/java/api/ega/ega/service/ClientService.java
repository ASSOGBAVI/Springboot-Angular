package api.ega.ega.service;

import api.ega.ega.dto.AccountDto;
import api.ega.ega.dto.ClientDto;
import api.ega.ega.entity.Account;
import api.ega.ega.entity.Client;
import api.ega.ega.enums.Pays;
import api.ega.ega.repository.IAccountRepository;
import api.ega.ega.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService{

    @Autowired
    public IClientRepository clientRepository;

    @Autowired
    public IAccountRepository accountRepository;

    @Autowired

    public AccountService accountService;
    @Override
    public Client saveClient(ClientDto clientDto) {
        Client client = convertDtoToEntity(clientDto);
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto showOneClient(int id) {
        Client client = clientRepository.findById(id).get();
        return convertEntityToDto(client);
    }

    @Override
    public List<ClientDto> showAllClient() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ClientDto convertEntityToDto(Client client) {

        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setClientLastName(client.getClientLastName());
        clientDto.setClientFirstName(client.getClientFirstName());
        clientDto.setBirthDate(client.getBirthDate());
        clientDto.setSexe(client.getSexe());
        clientDto.setAddress(client.getAddress());
        clientDto.setPhone(client.getPhone());
        clientDto.setNationality(client.getNationality());
        clientDto.setCreationDate(client.getCreationDate());
        return clientDto;
    }

    @Override
    public Client convertDtoToEntity(ClientDto clientDto) {
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setClientLastName(clientDto.getClientLastName());
        client.setClientFirstName(clientDto.getClientFirstName());
        client.setBirthDate(clientDto.getBirthDate());
        client.setSexe(clientDto.getSexe());
        client.setAddress(clientDto.getAddress());
        client.setPhone(clientDto.getPhone());
        client.setNationality(clientDto.getNationality());
        client.setCreationDate(clientDto.getCreationDate());
        return client;
    }



    // Additional Repository Methods from repo

    public List<ClientDto> findByClientLastName(String clientLastName) {
        List<Client> clients = clientRepository.findByClientLastName(clientLastName);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findByPhone(String phone) {
        List<Client> clients = clientRepository.findByPhone(phone);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findBySexe(String sexe) {
        List<Client> clients = clientRepository.findBySexe(sexe);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    public List<ClientDto> findByAddress(String address) {
        List<Client> clients = clientRepository.findByAddress(address);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findByNationality(Pays nationality) {
        List<Client> clients = clientRepository.findByNationality(nationality);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findByClientFirstNameAndClientLastName(String firstName, String lastName) {
        List<Client> clients = clientRepository.findByClientFirstNameAndClientLastName(firstName, lastName);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findByBirthDateAfter(LocalDate birthDate) {
        List<Client> clients = clientRepository.findByBirthDateAfter(birthDate);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findByBirthDateBefore(LocalDate birthDate) {
        List<Client> clients = clientRepository.findByBirthDateBefore(birthDate);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findByBirthDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Client> clients = clientRepository.findByBirthDateBetween(startDate, endDate);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findByClientLastNameStartedByChar(String clientLastName) {
        List<Client> clients = clientRepository.findByClientLastNameStartedByChar(clientLastName);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findByBirthDate(LocalDate d1, LocalDate d2) {
        List<Client> clients = clientRepository.findByBirthDate(d1, d2);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findClientsWithAccounts() {
        List<Client> clients = clientRepository.findClientsWithAccounts();
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<ClientDto> findClientsWithoutAccounts() {
        List<Client> clients = clientRepository.findClientsWithoutAccounts();
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }



    public List<ClientDto> findByAccountType(String accountType) {
        //public List<ClientDto> findByAccountType(TypeOfAccount accountType) {

            List<Client> clients = clientRepository.findByAccountType(accountType);
        return clients.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    public int getNumberOfAccountsByClientId(int clientId) {
        return clientRepository.getNumberOfAccountsByClientId(clientId);
    }

    public int getNumberOfAccountsByClientLastName(String clientLastName) {
        return clientRepository.getNumberOfAccountsByClientLastName(clientLastName);
    }

    public ClientDto findClientByAccountNumber(String accountNumber) {
        Client client = clientRepository.findClientByAccountNumber(accountNumber);
        return convertEntityToDto(client);
    }

    public int countAccountByTypeForClient(int clientId, String accountType) {
        //public int countAccountByTypeForClient(int clientId, TypeOfAccount accountType) {

            return clientRepository.countAccountByTypeForClient(clientId, accountType);
    }
}
