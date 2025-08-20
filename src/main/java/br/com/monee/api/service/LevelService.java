package br.com.monee.api.service;

import br.com.monee.api.controller.mapper.LevelMapper;
import br.com.monee.api.entity.LevelEntity;
import br.com.monee.api.entity.dto.LevelRequestDTO;
import br.com.monee.api.entity.dto.LevelResponseDTO;
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
