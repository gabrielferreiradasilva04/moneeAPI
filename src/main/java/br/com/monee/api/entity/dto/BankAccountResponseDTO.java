package br.com.monee.api.entity.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BankAccountResponseDTO (UUID id, String accountName, String description,
                                      LocalDateTime createdDate, LocalDateTime lastModifiedDate,
                                      String icon, String color){
}
