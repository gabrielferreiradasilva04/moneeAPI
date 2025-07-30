package br.com.monee.api.entity.dto;

import br.com.monee.api.entity.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionRequestDTO(UUID id, BigDecimal amount, LocalDate date, boolean fixed,
                                    TransactionType transactionType, UUID bankAccountId) {
}
