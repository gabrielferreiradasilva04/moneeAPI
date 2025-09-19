package br.com.monee.api.service;

import br.com.monee.api.util.mapper.ChallengeMapper;
import br.com.monee.api.domain.challenge.ChallengeEntity;
import br.com.monee.api.domain.user.UserEntity;
import br.com.monee.api.domain.challenge.ChallengeRequestDTO;
import br.com.monee.api.domain.challenge.ChallengeResponseDTO;
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
