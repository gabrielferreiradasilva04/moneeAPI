package br.com.monee.api.controller;

import br.com.monee.api.controller.mapper.TagMapper;
import br.com.monee.api.entity.dto.TagDTO;
import br.com.monee.api.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody TagDTO tagDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.tagService.save(tagDto));
    }

}
