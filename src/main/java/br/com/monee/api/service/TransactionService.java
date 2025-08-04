package br.com.monee.api.service;

import br.com.monee.api.controller.mapper.TagMapper;
import br.com.monee.api.controller.mapper.TransactionMapper;
import br.com.monee.api.entity.TransactionEntity;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.entity.dto.TagDTO;
import br.com.monee.api.entity.dto.TransactionRequestDTO;
import br.com.monee.api.entity.dto.TransactionResponseDTO;
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

    public TransactionService (TransactionRepository transactionRepository,
                               TransactionMapper transactionMapper, UserService userService, TagMapper tagMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.userService = userService;
        this.tagMapper = tagMapper;
    }

    public TransactionResponseDTO save(UUID userId, TransactionRequestDTO transactionRequestDTO)  {
        var transactionEntity = this.transactionMapper.requestToEntity(transactionRequestDTO);
        UserEntity user = this.userService.getUserByUUID(userId);
        transactionEntity.setUser(user);
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

    public void addTag(UUID transactionId, TagDTO tagDto){
        var transactionOptional = this.transactionRepository.findById(transactionId);
        if ((transactionOptional.isEmpty())) throw new EntityNotFoundException("transação não encontrada");
        var transactionEntity = transactionOptional.get();
        transactionEntity.getTags().add(this.tagMapper.toEntity(tagDto));
        this.transactionRepository.save(transactionEntity);
    }
    
}
