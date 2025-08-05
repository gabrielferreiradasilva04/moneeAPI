package br.com.monee.api.repository;

import br.com.monee.api.entity.TagEntity;
import br.com.monee.api.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    @Query("""
            SELECT t
            FROM TransactionEntity t
            WHERE
            t.user.id = :userId
            """)
    List<TransactionEntity> findAllByUserId(@Param("userId") UUID userId);
    Optional<TransactionEntity> findByIdAndUserId(UUID transactionId, UUID userId);
}
