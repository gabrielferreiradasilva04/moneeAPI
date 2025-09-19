package br.com.monee.api.repository;

import br.com.monee.api.domain.transaction.category.TransactionCategoryEntity;
import br.com.monee.api.domain.transaction.category.TransactionCategoryResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategoryEntity, UUID> {

    @Query("""
            SELECT tc
            FROM TransactionCategoryEntity tc
            WHERE
            tc.user.id = :userId OR tc.user is NULL
            """)
    List<TransactionCategoryResponseDTO> getAllTransactionCategories(@Param("userId") UUID userId);

}
