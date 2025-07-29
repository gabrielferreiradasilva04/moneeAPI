package br.com.monee.api.controller;

import br.com.monee.api.controller.mapper.BankAccountResponseMapper;
import br.com.monee.api.controller.mapper.TransactionEntityResponseMapper;
import br.com.monee.api.entity.BankAccountEntity;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.service.BankAccountService;
import br.com.monee.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final TransactionEntityResponseMapper transactionEntityResponseMapper;
    private final BankAccountService bankAccountService;
    private final BankAccountResponseMapper bankAccountResponseMapper;

    public UserController(UserService userService, TransactionEntityResponseMapper transactionEntityResponseMapper, BankAccountService bankAccountService, BankAccountResponseMapper bankAccountResponseMapper){
        this.userService = userService;
        this.transactionEntityResponseMapper = transactionEntityResponseMapper;
        this.bankAccountService = bankAccountService;
        this.bankAccountResponseMapper = bankAccountResponseMapper;
    }

    @GetMapping("/{userId}/transactions")
    public ResponseEntity<?> getAllUserTransactions(@PathVariable String stringUserId){
        try {
            var uuid = UUID.fromString(stringUserId);
            var transactionsEntity = this.userService.listAllTransactions(uuid);
            return ResponseEntity
                    .ok()
                    .body(transactionsEntity
                            .stream()
                            .map(transactionEntityResponseMapper::toDto)
                            .toList());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("UUID inválido");
        }
    }

    @PostMapping("/{userId}/bank-accounts")
    public ResponseEntity<?> createBankAccount(
            @PathVariable UUID userId,
            @RequestBody BankAccountEntity bankAccountEntity) {

        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.bankAccountResponseMapper.toDto(
                        this.bankAccountService.save(userId, bankAccountEntity)
                ));
    }
}
