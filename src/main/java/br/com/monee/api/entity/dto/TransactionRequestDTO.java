package br.com.monee.api.entity.dto;

import br.com.monee.api.entity.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionRequestDTO(UUID id, BigDecimal amount, LocalDate date, boolean fixed,
                                    TransactionType transactionType,
                                    @NotNull (message = "Conta bancária não pode ser nula") UUID bankAccountId,
                                    @NotNull (message = "Categoria não pode ser nula") UUID transactionCategoryId) {
}
