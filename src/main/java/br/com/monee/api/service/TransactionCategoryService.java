package br.com.monee.api.service;

import br.com.monee.api.entity.TransactionCategoryEntity;
import br.com.monee.api.repository.TransactionCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionCategoryService {
    private final TransactionCategoryRepository transactionCategoryRepository;

    public TransactionCategoryService(TransactionCategoryRepository transactionCategoryRepository) {
        this.transactionCategoryRepository = transactionCategoryRepository;
    }

    public TransactionCategoryEntity getById(UUID transactionCategoryId) {
        return this.transactionCategoryRepository
                .findById(transactionCategoryId)
                .orElseThrow( () -> new EntityNotFoundException(
                        "Categoria de transação não encontrada"
                ));
    }
}
