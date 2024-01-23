package glsia6.com.compteManagement.dto;

import glsia6.com.compteManagement.enums.CompteStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CompteDto {
    private String id;
    private CompteStatus status;
    private Date dateCreation;


}
