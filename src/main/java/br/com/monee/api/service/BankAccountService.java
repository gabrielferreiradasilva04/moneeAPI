package br.com.monee.api.service;

import br.com.monee.api.entity.BankAccountEntity;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.exception.custom.UnprocessableEntityException;
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

    public BankAccountService(BankAccountRepository bankAccountRepository, UserService userService) {
        this.bankAccountRepository = bankAccountRepository;
        this.userService = userService;
    }

    public BankAccountEntity save ( UUID userId, BankAccountEntity bankAccountEntity ) {
        UserEntity userEntity = this.userService.getUserByUUID(userId);
        bankAccountEntity.setUser(userEntity);
        return this.bankAccountRepository.save(bankAccountEntity);
    }

    public List<BankAccountEntity> getUserBankAccounts(UUID userId){
        return this.bankAccountRepository.findByUserId(userId);
    }

    public BankAccountEntity update ( UUID accountId, BankAccountEntity bankAccountEntity ) {

        Optional<BankAccountEntity> optionalExistingAccount = this.bankAccountRepository.findById(accountId);
        if(optionalExistingAccount.isEmpty()) throw new EntityNotFoundException("Conta bancaria nao encontrada");

        var existingAccount = optionalExistingAccount.get();
        //atualizar os dados da conta existente
        existingAccount.setAccountName(bankAccountEntity.getAccountName());
        existingAccount.setIcon(bankAccountEntity.getIcon());
        existingAccount.setBankName(bankAccountEntity.getBankName());

        return this.bankAccountRepository.save(existingAccount);
    }
}
