package br.com.monee.api.service;

import br.com.monee.api.util.mapper.LevelMapper;
import br.com.monee.api.domain.level.LevelEntity;
import br.com.monee.api.domain.level.LevelRequestDTO;
import br.com.monee.api.domain.level.LevelResponseDTO;
import br.com.monee.api.repository.LevelRepository;
import org.springframework.stereotype.Service;

@Service
public class LevelService {
    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;

    public LevelService(LevelRepository levelRepository, LevelMapper levelMapper) {
        this.levelRepository = levelRepository;
        this.levelMapper = levelMapper;
    }

    public LevelResponseDTO save(LevelRequestDTO levelRequestDTO){
        LevelEntity levelEntity = this.levelMapper.requestToEntity(levelRequestDTO);
        return this.levelMapper.entityToResponse(this.levelRepository.save(levelEntity));
    }
}
