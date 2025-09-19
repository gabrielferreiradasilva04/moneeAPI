package br.com.monee.api.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDefaultDataDTO(UUID id, String name, String email, String phone, LocalDateTime createdDate,
                                 LocalDateTime lastModifiedDate) {
}
