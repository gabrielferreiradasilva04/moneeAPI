package br.com.monee.api.service;

import br.com.monee.api.entity.dto.TagDTO;
import br.com.monee.api.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagDTO save(TagDTO dto){
    return null;
    }
}
