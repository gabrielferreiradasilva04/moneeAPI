package br.com.monee.api.service;

import br.com.monee.api.controller.mapper.TagMapper;
import br.com.monee.api.controller.mapper.TransactionMapper;
import br.com.monee.api.entity.*;
import br.com.monee.api.entity.dto.TagDTO;
import br.com.monee.api.entity.dto.TransactionRequestDTO;
import br.com.monee.api.entity.dto.TransactionResponseDTO;
import br.com.monee.api.entity.dto.TransactionTagRequestDTO;
import br.com.monee.api.repository.TagRepository;
import br.com.monee.api.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final UserService userService;
    private final TagMapper tagMapper;
    private final BankAccountService bankAccountService;
    private final TagService tagService;
    private final TransactionCategoryService transactionCategoryService;

    public TransactionService (TransactionRepository transactionRepository,
                               TransactionMapper transactionMapper, UserService userService, TagMapper tagMapper,
                               BankAccountService bankAccountService, TagService tagService,
                               TransactionCategoryService transactionCategoryService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.userService = userService;
        this.tagMapper = tagMapper;
        this.bankAccountService = bankAccountService;
        this.tagService = tagService;
        this.transactionCategoryService = transactionCategoryService;
    }

    public TransactionResponseDTO save(UUID userId, TransactionRequestDTO transactionRequestDTO)  {
        UserEntity user = this.userService.getUserByUUID(userId);
        BankAccountEntity bank = this.bankAccountService.getByIdAndUserId(transactionRequestDTO.bankAccountId(), userId);
        TransactionCategoryEntity transactionCategoryEntity = this.transactionCategoryService.getById(
                transactionRequestDTO.transactionCategoryId()
        );
        TransactionEntity transactionEntity = this.transactionMapper.requestToEntity(transactionRequestDTO);
        transactionEntity.setTransactionBank(bank);
        transactionEntity.setUser(user);
        transactionEntity.setTransactionCategory(transactionCategoryEntity);

        return this.transactionMapper
                .toResponseDto(this.transactionRepository.save(transactionEntity));
    }
    
    public List<TransactionResponseDTO> getAllUserTransactions(UUID userId ) {
        List<TransactionEntity> userTransactions = this.transactionRepository.findAllByUserId(userId);
        return userTransactions
                .stream()
                .map(this.transactionMapper::toResponseDto)
                .toList();
    }

    public void addTags(UUID userId, UUID transactionId, List<UUID> tagIds){
        TransactionEntity transactionEntity = this.transactionRepository.findByIdAndUserId(transactionId, userId)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Transação não encontrada"
                ));
        List<TagEntity> tags = this.tagService.getAllById(tagIds);

        transactionEntity.setTags(tags);
        this.transactionRepository.save(transactionEntity);
    }
    
}
