package br.com.monee.api.entity.dto;

import br.com.monee.api.entity.UserRole;

public record UserEntityRequestDTO(String name, String phone, String email, String password, UserRole userRole) {
}
