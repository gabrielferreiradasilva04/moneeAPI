package br.com.monee.api.repository;

import br.com.monee.api.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    @Query("""
            SELECT t
            FROM TransactionEntity
            WHERE
            t.user.id = :userId
            """)
    List<TransactionEntity> findAllByUserId(@Param("userId") UUID userId);
}
