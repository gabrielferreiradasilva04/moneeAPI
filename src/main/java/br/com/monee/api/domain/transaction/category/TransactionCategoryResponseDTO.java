package br.com.monee.api.domain.transaction.category;

import java.util.UUID;

public record TransactionCategoryResponseDTO(UUID id, String title, String description, String icon, String color) {
}
