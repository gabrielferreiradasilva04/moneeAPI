package br.com.monee.api.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreditCardRequestDTO(String carName, BigDecimal creditLimit, LocalDate closingDate,
                                   LocalDate dueDate, UUID bankAccount) {
}
