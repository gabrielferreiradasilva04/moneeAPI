package br.com.monee.api.entity.dto;

import java.util.UUID;

public record LevelResponseDTO (UUID id, String title, String description, int xpMinimum, int xpMaximum){
}
