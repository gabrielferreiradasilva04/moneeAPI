package br.com.monee.api.domain.transaction.tag;

import java.util.UUID;

public record TagDTO(UUID id, String title, String color) {
}
