package br.com.monee.api.domain.quiz.question;

import br.com.monee.api.domain.user.UserQuizEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "user_answers")
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn
    private UserQuizEntity userQuiz;
    @ManyToOne
    @JoinColumn
    private QuizQuestionEntity quizQuestion;
    @ManyToOne
    @JoinColumn
    private QuestionOptionEntity questionOption;
}
