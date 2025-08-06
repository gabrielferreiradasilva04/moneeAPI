package br.com.monee.api.service;

import br.com.monee.api.controller.mapper.BankAccountMapper;
import br.com.monee.api.entity.BankAccountEntity;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.entity.dto.BankAccountRequestDTO;
import br.com.monee.api.entity.dto.BankAccountResponseDTO;
import br.com.monee.api.repository.BankAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserService userService;
    private final BankAccountMapper bankAccountMapper;

    public BankAccountService(BankAccountRepository bankAccountRepository, UserService userService, BankAccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.userService = userService;
        this.bankAccountMapper = bankAccountMapper;
    }

    public BankAccountResponseDTO save (UUID userId, BankAccountRequestDTO bankAccountRequestDTO ) {
        BankAccountEntity bankAccountEntity = this.bankAccountMapper.requestToEntity(bankAccountRequestDTO);
        UserEntity userEntity = this.userService.getUserByUUID(userId);
        bankAccountEntity.setUser(userEntity);
        return this.bankAccountMapper.toResponseDto(this.bankAccountRepository.save(bankAccountEntity));
    }

    public List<BankAccountEntity> getUserBankAccounts(UUID userId){
        return this.bankAccountRepository.findByUserId(userId);
    }

    public BankAccountEntity update ( UUID accountId, BankAccountRequestDTO bankAccountRequestDTO ) {

        BankAccountEntity bankAccountEntity = this.bankAccountMapper.requestToEntity(bankAccountRequestDTO);
        Optional<BankAccountEntity> optionalExistingAccount = this.bankAccountRepository.findById(accountId);
        if(optionalExistingAccount.isEmpty()) throw new EntityNotFoundException("Conta bancaria nao encontrada");

        var existingAccount = optionalExistingAccount.get();
        //atualizar os dados da conta existente
        existingAccount.setAccountName(bankAccountEntity.getAccountName());
        existingAccount.setIcon(bankAccountEntity.getIcon());
        existingAccount.setDescription(bankAccountEntity.getDescription());

        return this.bankAccountRepository.save(existingAccount);
    }

    public BankAccountEntity getById(UUID bankId){
        return this.bankAccountRepository.findById(bankId)
                .orElseThrow(() ->  new EntityNotFoundException(
                        ("Conta Banária não encontrada")
                ));
    }

    public BankAccountEntity getByIdAndUserId(UUID bankId, UUID userId){
        return this.bankAccountRepository.findByIdAndUserId(bankId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Conta bancária não encontrada para este usuário"
                ));
    }
}
