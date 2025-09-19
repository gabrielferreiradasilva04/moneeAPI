package br.com.monee.api.domain.transaction;

import br.com.monee.api.domain.transaction.tag.TagEntity;
import br.com.monee.api.domain.transaction.category.TransactionCategoryEntity;
import br.com.monee.api.domain.user.UserEntity;
import br.com.monee.api.domain.bankAccount.BankAccountEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String description;
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDate date;
    private boolean fixed;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(nullable = false)
    private BankAccountEntity transactionBank;
    @ManyToMany
    @JoinTable(
            name = "transaction_tag",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "transaction_category_id", nullable = false)
    private TransactionCategoryEntity transactionCategory;
}
