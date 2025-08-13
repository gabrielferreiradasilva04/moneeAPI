package br.com.monee.api.entity.dto;

import br.com.monee.api.entity.FinancialGoalType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record FinancialGoalResponseDTO(UUID id, String title, String description, LocalDate dueDate,
                                       BigDecimal amount, LocalDate createdDate, LocalDate lastModifiedDate,
                                       FinancialGoalType financialGoalType) {
}
