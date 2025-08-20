package br.com.monee.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class ChallengeEntity {
    private UUID id;
    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, length = 700)
    private String description;
    private int xpReward;
    private LocalDate completedOn;
    @CreatedDate
    private LocalDate createdOn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChallengeStatus status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity user;


}
