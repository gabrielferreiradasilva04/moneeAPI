package br.com.monee.api.entity.dto;

import br.com.monee.api.entity.ChallengeStatus;

import java.time.LocalDate;
import java.util.UUID;

public record ChallengeResponseDTO(UUID id, String title, String description, int xpReward, LocalDate completedOn,
                                   LocalDate createdOn, ChallengeStatus challengeStatus){
}
