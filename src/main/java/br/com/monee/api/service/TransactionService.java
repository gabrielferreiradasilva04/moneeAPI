package br.com.monee.api.service;

import br.com.monee.api.controller.mapper.TransactionMapper;
import br.com.monee.api.entity.TransactionEntity;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.entity.dto.TransactionRequestDTO;
import br.com.monee.api.entity.dto.TransactionResponseDTO;
import br.com.monee.api.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final UserService userService;

    public TransactionService (TransactionRepository transactionRepository,
                               TransactionMapper transactionMapper, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.userService = userService;
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
    
}
