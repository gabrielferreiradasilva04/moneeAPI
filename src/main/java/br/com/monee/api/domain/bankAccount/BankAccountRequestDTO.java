package br.com.monee.api.domain.bankAccount;

public record BankAccountRequestDTO(String accountName, String description,
                                    String icon, String color) {
}
