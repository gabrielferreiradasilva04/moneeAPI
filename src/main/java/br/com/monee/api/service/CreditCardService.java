package br.com.monee.api.service;

import br.com.monee.api.controller.mapper.CreditCardMapper;
import br.com.monee.api.entity.BankAccountEntity;
import br.com.monee.api.entity.CreditCardEntity;
import br.com.monee.api.entity.dto.CreditCardRequestDTO;
import br.com.monee.api.entity.dto.CreditCardResponseDTO;
import br.com.monee.api.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;
    private final BankAccountService bankAccountService;

    public CreditCardService(CreditCardRepository creditCardRepository, CreditCardMapper creditCardMapper, BankAccountService bankAccountService) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardMapper = creditCardMapper;
        this.bankAccountService = bankAccountService;
    }

    public CreditCardResponseDTO save(UUID bankId, CreditCardRequestDTO creditCardRequestDTO){
        BankAccountEntity bankAccountEntity = this.bankAccountService.getById(bankId);
        CreditCardEntity creditCardEntity = this.creditCardMapper.requestToEntity(creditCardRequestDTO);
        creditCardEntity.setBankAccount(bankAccountEntity);
        return this.creditCardMapper.entityToResponse(this.creditCardRepository.save(creditCardEntity));
    }
}
