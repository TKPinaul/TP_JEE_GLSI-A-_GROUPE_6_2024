package glsia6.com.compteManagement.mappers;

import glsia6.com.compteManagement.dto.ClientDto;
import glsia6.com.compteManagement.entity.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientMapperImpl {

    public ClientDto fromClient(Client client){
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client,clientDto);
        /*clientDto.setId(clientDto.getId());
        clientDto.setNom(clientDto.getNom());
        clientDto.setTelephone(clientDto.getTelephone());
        clientDto.setSexe(clientDto.getSexe());
        clientDto.setCourriel(clientDto.getCourriel());
        clientDto.setAdresse(clientDto.getAdresse());
        clientDto.setNationalite(clientDto.getNationalite());*/
        return clientDto;
    }

    public Client fromClientDto(ClientDto clientDto){
        Client client = new Client();
        BeanUtils.copyProperties(clientDto,client);
        return client;
    };


}
