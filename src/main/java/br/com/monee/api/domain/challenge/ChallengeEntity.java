package br.com.monee.api.domain.challenge;

import br.com.monee.api.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "challenge")
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class ChallengeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
