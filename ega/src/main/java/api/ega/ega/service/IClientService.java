package api.ega.ega.service;

import api.ega.ega.dto.ClientDto;
import api.ega.ega.entity.Client;

import java.util.List;

public interface IClientService {
    public Client saveClient(ClientDto clientDto);
    public void deleteClient(int id);
    public ClientDto showOneClient(int id);
    public List<ClientDto> showAllClient();
    public ClientDto convertEntityToDto(Client client);
    public Client convertDtoToEntity(ClientDto clientDto);

}
