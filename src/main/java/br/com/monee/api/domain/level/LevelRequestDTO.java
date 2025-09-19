package br.com.monee.api.domain.level;

public record LevelRequestDTO(String title, String description, int xpMinimum, int xpMaximum) {
}
