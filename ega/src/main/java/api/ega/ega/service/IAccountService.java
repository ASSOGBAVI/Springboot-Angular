package api.ega.ega.service;

import api.ega.ega.dto.AccountDto;
import api.ega.ega.entity.Account;

import java.util.List;

public interface IAccountService {
    public Account saveAccount(AccountDto accountDto);
    public void deleteAccount(int id);
    public AccountDto showOneAccount(int id);
    public List<AccountDto> showAllAccount();
    public AccountDto convertEntityToDto(Account account);
    public Account convertDtoToEntity(AccountDto accountDto);
}
