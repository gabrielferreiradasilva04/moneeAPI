package br.com.monee.api.service;

import br.com.monee.api.entity.TransactionEntity;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.repository.TransactionRepository;
import br.com.monee.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public UserService(UserRepository userRepository, TransactionRepository transactionRepository){
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public void save(UserEntity user){
        this.userRepository.save(user);
    }

    public List<TransactionEntity> listAllTransactions(UUID userId){
       return this.transactionRepository.findAllByUserId(userId);
    }




}
