package br.com.monee.api.controller;

import br.com.monee.api.service.LevelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/levels")
public class LevelController {
    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }
}
