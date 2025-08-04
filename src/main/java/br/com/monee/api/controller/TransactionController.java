package br.com.monee.api.controller;

import br.com.monee.api.controller.mapper.TransactionMapper;
import br.com.monee.api.entity.dto.TagDTO;
import br.com.monee.api.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping("/{transactionId}/tags")
    public ResponseEntity<?> addTag(@PathVariable UUID transactionId, @RequestBody TagDTO tagDto){
        this.transactionService.addTag(transactionId, tagDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
