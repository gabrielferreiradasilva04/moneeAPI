package br.com.monee.api.domain.bankAccount;


import br.com.monee.api.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bank_account")
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 200)
    private String accountName;
    @Column(nullable = false, length = 800)
    private String description;
    @Column(length = 200)
    private String icon;
    @Column(length = 200)
    private String color;

    //Variáveis de auditoria
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    //Relacionamentos
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}
