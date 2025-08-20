package br.com.monee.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user_quiz")
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class UserQuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int xpObtained;
    private LocalDate completedOn;
    private String aiFeedback;

    @ManyToOne
    @JoinColumn
    private QuizEntity quiz;
    @ManyToOne
    @JoinColumn
    private UserEntity user;

}
