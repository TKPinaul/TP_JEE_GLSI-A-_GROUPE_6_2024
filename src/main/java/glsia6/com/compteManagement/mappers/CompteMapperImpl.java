package glsia6.com.compteManagement.mappers;

import glsia6.com.compteManagement.dto.CompteDto;
import glsia6.com.compteManagement.entity.Compte;

public class CompteMapperImpl {
    public CompteDto formCompte(Compte compte){
        CompteDto compteDto = new CompteDto();
        compteDto.setStatus(compte.getStatus());
        return null;


    }
}
