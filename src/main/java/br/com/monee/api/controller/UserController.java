package br.com.monee.api.controller;

import br.com.monee.api.controller.mapper.BankAccountMapper;
import br.com.monee.api.controller.mapper.TransactionMapper;
import br.com.monee.api.entity.BankAccountEntity;
import br.com.monee.api.entity.TransactionEntity;
import br.com.monee.api.entity.dto.BankAccountRequestDTO;
import br.com.monee.api.entity.dto.TagDTO;
import br.com.monee.api.entity.dto.TransactionRequestDTO;
import br.com.monee.api.service.BankAccountService;
import br.com.monee.api.service.TagService;
import br.com.monee.api.service.TransactionService;
import br.com.monee.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final TransactionMapper transactionMapper;
    private final BankAccountService bankAccountService;
    private final TransactionService transactionService;
    private final TagService tagService;

    public UserController(UserService userService, TransactionMapper transactionMapper,
                          BankAccountService bankAccountService, TransactionService transactionService, TagService tagService)
    {
        this.userService = userService;
        this.transactionMapper = transactionMapper;
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
        this.tagService = tagService;
    }

    @GetMapping("/{userId}/transactions")
    public ResponseEntity<?> getAllUserTransactions(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(
                this.transactionService.getAllUserTransactions(userId)
        );
    }
    @PostMapping("/{userId}/transactions")
    public ResponseEntity<?> createTransaction(@PathVariable UUID userId,
                                               @RequestBody @Valid TransactionRequestDTO transactionRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.transactionService.save(userId, transactionRequestDTO));
    }

    @PostMapping("/{userId}/bank-accounts")
    public ResponseEntity<?> createBankAccount(@PathVariable UUID userId,
                                               @RequestBody @Valid BankAccountRequestDTO bankAccountRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.bankAccountService.save(userId, bankAccountRequestDTO));
    }

    @PostMapping("/{userId}/tags")
    public ResponseEntity<?> createUserTag(@PathVariable UUID userId,
                                           @RequestBody TagDTO tagDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.tagService.saveUserTag(tagDto, userId));

    }
}
