package br.com.monee.api.service;

import br.com.monee.api.controller.mapper.TagMapper;
import br.com.monee.api.entity.TagEntity;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.entity.dto.TagDTO;
import br.com.monee.api.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final UserService userService;

    public TagService(TagRepository tagRepository, TagMapper tagMapper, UserService userService) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
        this.userService = userService;
    }

    public TagDTO saveUserTag(TagDTO dto, UUID userId){
        UserEntity user = this.userService.getUserByUUID(userId);
        TagEntity tagEntity = this.tagMapper.toEntity(dto);
        tagEntity.setUser(user);
        return this.tagMapper.toDTO(this.tagRepository.save(tagEntity));
    }
    public void delete(UUID tagId){
        Optional<TagEntity> optTagEntity = this.tagRepository.findById(tagId);
        if(optTagEntity.isEmpty()) throw new EntityNotFoundException("Tag não encontrada");
        this.tagRepository.delete(optTagEntity.get());
    }
    public List<TagEntity> getAllById(List<UUID> tagIds){
        return this.tagRepository.findAllById(tagIds);
    }
}
