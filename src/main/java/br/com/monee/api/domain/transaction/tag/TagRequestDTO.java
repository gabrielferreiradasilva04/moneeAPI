package br.com.monee.api.domain.transaction.tag;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record TagRequestDTO(@NotNull(message = "tags não pode ser null") List<UUID> tagIds){
}
