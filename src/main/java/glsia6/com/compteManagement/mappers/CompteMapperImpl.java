package glsia6.com.compteManagement.mappers;

import glsia6.com.compteManagement.dto.CompteCourantDto;
import glsia6.com.compteManagement.dto.CompteEpargneDto;
import glsia6.com.compteManagement.entity.CompteCourant;
import glsia6.com.compteManagement.entity.CompteEpargne;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteMapperImpl {
    @Autowired
    private ClientMapperImpl clientMapper;
    public CompteEpargneDto formCompteEpargne(CompteEpargne compteEpargne ){
        CompteEpargneDto compteEpargneDto = new CompteEpargneDto();
        BeanUtils.copyProperties(compteEpargne,compteEpargneDto);
        compteEpargneDto.setClientDto(clientMapper.fromClient(compteEpargne.getClient()));
        compteEpargneDto.setType(compteEpargne.getClass().getSimpleName());
        return compteEpargneDto;


    }

    public CompteEpargne fromCompteEpargneDto(CompteEpargneDto compteEpargneDto){
        CompteEpargne compteEpargne = new CompteEpargne();
        BeanUtils.copyProperties(compteEpargneDto,compteEpargne);
        compteEpargne.setClient(clientMapper.fromClientDto(compteEpargneDto.getClientDto()));
        return compteEpargne;
    }

    public CompteCourantDto fromCompteCourant(CompteCourant compteCourant){
        CompteCourantDto compteCourantDto = new CompteCourantDto();
        BeanUtils.copyProperties(compteCourant,compteCourantDto);
        compteCourantDto.setClientDto(clientMapper.fromClient(compteCourant.getClient()));
        compteCourantDto.setType(compteCourant.getClass().getSimpleName());


        return compteCourantDto;

    }

    public CompteCourant fromCompteCourantDto(CompteCourantDto compteCourantDto){
        CompteCourant compteCourant = new CompteCourant();
        BeanUtils.copyProperties(compteCourantDto,compteCourant);
        compteCourant.setClient(clientMapper.fromClientDto(compteCourantDto.getClientDto()));


        return compteCourant;
    }
}
