package br.com.monee.api.repository;

import br.com.monee.api.entity.TransactionCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategoryEntity, UUID> {
}
