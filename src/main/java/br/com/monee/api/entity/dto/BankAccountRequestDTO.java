package br.com.monee.api.entity.dto;

public record BankAccountRequestDTO(String accountName, String description,
                                    String icon, String color) {
}
