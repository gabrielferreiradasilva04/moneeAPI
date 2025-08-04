package br.com.monee.api.entity.dto;

import java.util.UUID;

public record TagDTO(UUID id, String title, String color) {
}
