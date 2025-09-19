package br.com.monee.api.domain.transaction.category;

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
@Table(name = "transaction_category")
public class TransactionCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private String icon;
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public TransactionCategoryEntity(String title, String description, String icon, String color){
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.color = color;
    }
}
