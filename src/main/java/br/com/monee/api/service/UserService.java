package br.com.monee.api.service;

import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void save(UserEntity user){
        this.userRepository.save(user);
    }



}
