package br.com.monee.api.service;

import br.com.monee.api.controller.mapper.TransactionCategoryMapper;
import br.com.monee.api.entity.TransactionCategoryEntity;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.entity.dto.TransactionCategoryRequestDTO;
import br.com.monee.api.entity.dto.TransactionCategoryResponseDTO;
import br.com.monee.api.repository.TransactionCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionCategoryService {
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final UserService userService;
    private final TransactionCategoryMapper transactionCategoryMapper;

    public TransactionCategoryService(TransactionCategoryRepository transactionCategoryRepository, UserService userService, TransactionCategoryMapper transactionCategoryMapper) {
        this.transactionCategoryRepository = transactionCategoryRepository;
        this.userService = userService;
        this.transactionCategoryMapper = transactionCategoryMapper;
    }

    public TransactionCategoryEntity getById(UUID transactionCategoryId) {
        return this.transactionCategoryRepository
                .findById(transactionCategoryId)
                .orElseThrow( () -> new EntityNotFoundException(
                        "Categoria de transação não encontrada"
                ));
    }

    public List<TransactionCategoryResponseDTO> getAllTransactions(UUID userId){
        return this.transactionCategoryRepository.getAllTransactionCategories(userId);
    }

    public TransactionCategoryResponseDTO save(UUID userId, TransactionCategoryRequestDTO transactionCategoryRequestDTO){
        UserEntity user = this.userService.getUserByUUID(userId);
        TransactionCategoryEntity entity = this.transactionCategoryMapper.requestToEntity(transactionCategoryRequestDTO);

        entity.setUser(user);
        return this.transactionCategoryMapper.entityToResponse(this.transactionCategoryRepository.save(entity));
    }
}
