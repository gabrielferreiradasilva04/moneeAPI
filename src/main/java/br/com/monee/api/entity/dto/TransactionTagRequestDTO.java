package br.com.monee.api.entity.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record TransactionTagRequestDTO (@NotNull(message = "tags não pode ser null") List<UUID> tagIds){
}
