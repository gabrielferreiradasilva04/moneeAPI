package br.com.monee.api.entity.dto;

import java.util.UUID;

public record TransactionCategoryResponseDTO(UUID id, String title, String description, String icon, String color) {
}
