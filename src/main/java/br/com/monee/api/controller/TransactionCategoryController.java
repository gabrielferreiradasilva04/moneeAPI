package br.com.monee.api.controller;

import br.com.monee.api.service.TransactionCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transaction-categories")
public class TransactionCategoryController {

    private final TransactionCategoryService transactionCategoryService;


    public TransactionCategoryController(TransactionCategoryService transactionCategoryService) {
        this.transactionCategoryService = transactionCategoryService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllTransactions(@PathVariable UUID userId){
        return ResponseEntity.ok().body(this.transactionCategoryService.getAllTransactions(userId));
    }


}
