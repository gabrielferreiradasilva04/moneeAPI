package br.com.monee.api.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionResponseDTO(UUID id, String description, BigDecimal amount, LocalDate date,
                                     boolean fixed, TransactionType transactionType) {
}
