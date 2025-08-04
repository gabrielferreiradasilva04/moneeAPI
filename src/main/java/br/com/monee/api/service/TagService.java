package br.com.monee.api.service;

import br.com.monee.api.controller.mapper.TagMapper;
import br.com.monee.api.entity.TagEntity;
import br.com.monee.api.entity.dto.TagDTO;
import br.com.monee.api.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public TagDTO save(TagDTO dto){
        var entity = this.tagMapper.toEntity(dto);
        return this.tagMapper.toDTO(this.tagRepository.save(entity));
    }
    public void delete(UUID tagId){
        Optional<TagEntity> optTagEntity = this.tagRepository.findById(tagId);
        if(optTagEntity.isEmpty()) throw new EntityNotFoundException("Tag não encontrada");
        this.tagRepository.delete(optTagEntity.get());
    }
}
