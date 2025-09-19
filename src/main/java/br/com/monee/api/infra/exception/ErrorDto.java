package br.com.monee.api.infra.exception;

import java.util.List;

public record ErrorDto(String field, String error) {
}
