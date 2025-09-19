package br.com.monee.api.domain.creditCard;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreditCardRequestDTO(String carName, BigDecimal creditLimit, LocalDate closingDate,
                                   LocalDate dueDate) {
}
