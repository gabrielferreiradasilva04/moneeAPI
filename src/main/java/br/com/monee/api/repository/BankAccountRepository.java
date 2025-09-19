package br.com.monee.api.repository;

import br.com.monee.api.domain.bankAccount.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, UUID> {

    List<BankAccountEntity> findByUserId( UUID userId) ;
    Optional<BankAccountEntity> findByIdAndUserId(UUID bankId, UUID userId);
}
