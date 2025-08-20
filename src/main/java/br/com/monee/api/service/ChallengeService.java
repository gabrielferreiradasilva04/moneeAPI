package br.com.monee.api.service;

import br.com.monee.api.controller.mapper.ChallengeMapper;
import br.com.monee.api.entity.ChallengeEntity;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.entity.dto.ChallengeRequestDTO;
import br.com.monee.api.entity.dto.ChallengeResponseDTO;
import br.com.monee.api.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserService userService;
    private final ChallengeMapper challengeMapper;

    public ChallengeService(ChallengeRepository challengeRepository, UserService userService, ChallengeMapper challengeMapper) {
        this.challengeRepository = challengeRepository;
        this.userService = userService;
        this.challengeMapper = challengeMapper;
    }

    public ChallengeResponseDTO save(UUID userId, ChallengeRequestDTO challengeRequestDTO){
        UserEntity user = this.userService.getUserByUUID(userId);
        ChallengeEntity challengeEntity = this.challengeMapper.requestToEntity(challengeRequestDTO);
        challengeEntity.setUser(user);
        return this.challengeMapper.entityToResponse(this.challengeRepository.save(challengeEntity));
    }
}
