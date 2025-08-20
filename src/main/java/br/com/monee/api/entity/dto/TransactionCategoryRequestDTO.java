package br.com.monee.api.entity.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TransactionCategoryRequestDTO(@NotNull(message = "Campo obrigatorio") String title, String description, String icon, String color) {
}
