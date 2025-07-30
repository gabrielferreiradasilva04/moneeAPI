package br.com.monee.api.service;

import br.com.monee.api.entity.TransactionEntity;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.repository.TransactionRepository;
import br.com.monee.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public UserEntity getUserByUUID(UUID id){
        Optional<UserEntity> optional = this.userRepository.findById(id);
        if (optional.isEmpty()) throw new EntityNotFoundException("Usuário não encontrado");
        return optional.get();
    }




}
