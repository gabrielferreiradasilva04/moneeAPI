package br.com.monee.api.controller;
import br.com.monee.api.domain.bankAccount.BankAccountRequestDTO;
import br.com.monee.api.domain.creditCard.CreditCardRequestDTO;
import br.com.monee.api.domain.transaction.TransactionRequestDTO;
import br.com.monee.api.domain.transaction.category.TransactionCategoryRequestDTO;
import br.com.monee.api.domain.transaction.tag.TagDTO;
import br.com.monee.api.domain.transaction.tag.TagRequestDTO;
import br.com.monee.api.util.mapper.TransactionMapper;
import br.com.monee.api.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final CreditCardService creditCardService;
    private final TransactionCategoryService transactionCategoryService;


    public UserController(UserService userService, TransactionMapper transactionMapper,
                          BankAccountService bankAccountService, TransactionService transactionService, TagService tagService, CreditCardService creditCardService, TransactionCategoryService transactionCategoryService)
    {
        this.userService = userService;
        this.transactionMapper = transactionMapper;
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
        this.tagService = tagService;
        this.creditCardService = creditCardService;
        this.transactionCategoryService = transactionCategoryService;
    }

    @Operation(summary = "GET para listar todas as transações de um cliente",
    description = "Lista todas as transações do cliente informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de transações retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao retornar lista de transações"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
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

    @PostMapping("/{userId}/transactions/{transactionId}/tags")
    public ResponseEntity<?> addTagsToTransaction(@PathVariable UUID userId,
                                                  @PathVariable UUID transactionId,
                                                  @RequestBody TagRequestDTO tagsId){
        this.transactionService.addTags(userId, transactionId, tagsId.tagIds());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{userId}/tags")
    public ResponseEntity<?> createUserTag(@PathVariable UUID userId,
                                           @RequestBody TagDTO tagDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.tagService.saveUserTag(tagDto, userId));

    }

    @PostMapping("/{userId}/bank-accounts")
    public ResponseEntity<?> createBankAccount(@PathVariable UUID userId,
                                               @RequestBody @Valid BankAccountRequestDTO bankAccountRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.bankAccountService.save(userId, bankAccountRequestDTO));
    }

    @PostMapping("/{userId}/bank-accounts/{bankId}/credit-cards")
    public ResponseEntity<?> createCreditCard(@PathVariable UUID bankId, @RequestBody CreditCardRequestDTO creditCardRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.creditCardService.save(bankId, creditCardRequestDTO));

    }

    @PostMapping("/{userId}/transaction-categories")
    public ResponseEntity<?> createTransactionCategory (@PathVariable UUID userId,
                                                        @RequestBody TransactionCategoryRequestDTO transactionCategoryRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.transactionCategoryService.save(userId, transactionCategoryRequestDTO)
        );
    }

    @PostMapping("/{userId}/challenges/create-challenges")
    public ResponseEntity<?> createChallenge(@PathVariable UUID userId){
        this.userService.createChallenges(userId);
        return ResponseEntity.ok().build();
    }
}