package br.com.monee.api.entity.dto;

import br.com.monee.api.entity.ChallengeStatus;

import java.time.LocalDate;

public record ChallengeRequestDTO(String title, String description, int xpReward, ChallengeStatus status,
                                  LocalDate completedOn) {
}
