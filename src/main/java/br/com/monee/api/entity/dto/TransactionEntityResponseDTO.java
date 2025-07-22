package br.com.monee.api.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionEntityResponseDTO(UUID id, BigDecimal amount, LocalDate date, boolean fixed ) {
}
