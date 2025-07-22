package br.com.monee.api.controller;

import br.com.monee.api.controller.mapper.TransactionEntityResponseMapper;
import br.com.monee.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final TransactionEntityResponseMapper transactionEntityResponseMapper;

    public UserController(UserService userService, TransactionEntityResponseMapper transactionEntityResponseMapper){
        this.userService = userService;
        this.transactionEntityResponseMapper = transactionEntityResponseMapper;
    }

    @GetMapping("/{userId}/transactions")
    public ResponseEntity<?> getAllUserTransactions(@PathVariable String stringUserId){
        try {
            var uuid = UUID.fromString(stringUserId);
            var transactionsEntity = this.userService.listAllTransactions(uuid);
            return ResponseEntity.ok().body(transactionsEntity.stream().map(transactionEntityResponseMapper::toDto).toList());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("UUID inválido");
        }

    }
}
