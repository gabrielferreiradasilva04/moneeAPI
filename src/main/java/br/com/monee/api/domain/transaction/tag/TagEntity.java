package br.com.monee.api.domain.transaction.tag;

import br.com.monee.api.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    private String color;
    @ManyToOne
    @JoinColumn
    private UserEntity user;

    public TagEntity(String title, String color){
        this.title = title;
        this.color = color;
    }
}
