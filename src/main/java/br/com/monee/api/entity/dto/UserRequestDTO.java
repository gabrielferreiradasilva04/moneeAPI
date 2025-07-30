package br.com.monee.api.entity.dto;

import br.com.monee.api.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(@NotBlank(message = "Campo obbrigatório") String name,
                             @NotBlank(message = "Campo obbrigatório")  String phone,
                             @NotBlank(message = "Campo obbrigatório") String email,
                             @NotBlank(message = "Campo obbrigatório") String password,
                             @NotNull(message = "Campo obbrigatório") UserRole userRole) {
}
