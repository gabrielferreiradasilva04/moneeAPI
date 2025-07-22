package br.com.monee.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    private BigDecimal amount;
    private LocalDate date;
    private boolean fixed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserEntity user;
}
