package br.com.monee.api.domain.transaction.category;

import jakarta.validation.constraints.NotNull;

public record TransactionCategoryRequestDTO(@NotNull(message = "Campo obrigatorio") String title, String description, String icon, String color) {
}
