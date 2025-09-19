package br.com.monee.api.domain.level;

import java.util.UUID;

public record LevelResponseDTO (UUID id, String title, String description, int xpMinimum, int xpMaximum){
}
