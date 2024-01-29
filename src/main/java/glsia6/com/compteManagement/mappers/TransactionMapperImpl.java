package glsia6.com.compteManagement.mappers;

import glsia6.com.compteManagement.dto.TransactionDto;
import glsia6.com.compteManagement.entity.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapperImpl {
    public TransactionDto fromTransaction(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        BeanUtils.copyProperties(transaction,transactionDto);
        return transactionDto;
    }

}
