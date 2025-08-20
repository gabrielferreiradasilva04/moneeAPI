package br.com.monee.api.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreditCardResponseDTO(UUID id, String cardName, BigDecimal creditLimit,
                                    LocalDate closingDate, LocalDate dueDate) {
}
