package br.com.monee.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "quiz")
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, length = 700)
    private String description;
    private int xpReward;
    @CreatedDate
    private LocalDate createdOn;
    private int minimumScore;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuizStatus quizStatus;

    @ManyToOne
    @JoinColumn(nullable = false)
    private LevelEntity level;

    @ManyToMany
    @JoinTable(
            name = "quiz_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<QuizQuestionEntity> questions = new ArrayList<>();

}
