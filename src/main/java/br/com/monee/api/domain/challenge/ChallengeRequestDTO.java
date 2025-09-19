package br.com.monee.api.domain.challenge;

import java.time.LocalDate;

public record ChallengeRequestDTO(String title, String description, int xpReward, ChallengeStatus status,
                                  LocalDate completedOn) {
}
