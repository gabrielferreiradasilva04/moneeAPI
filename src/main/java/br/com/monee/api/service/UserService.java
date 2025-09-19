package br.com.monee.api.service;

import br.com.monee.api.util.mapper.UserMapper;
import br.com.monee.api.domain.user.UserEntity;
import br.com.monee.api.domain.user.UserDefaultDataDTO;
import br.com.monee.api.repository.TransactionRepository;
import br.com.monee.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, TransactionRepository transactionRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.userMapper = userMapper;
    }

    public void save(UserEntity user){
        this.userRepository.save(user);
    }

    public UserEntity getUserByUUID(UUID id){
        return this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuário não encontrado"
                ));
    }
    public void createChallenges(UUID userId){

    };
    public UserDefaultDataDTO getUserDefaultData(UserEntity entity){
        return this.userMapper.toDefaultDataDTO(entity);
    }
}
