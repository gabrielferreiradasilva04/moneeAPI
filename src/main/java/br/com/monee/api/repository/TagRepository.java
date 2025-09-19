package br.com.monee.api.repository;

import br.com.monee.api.domain.transaction.tag.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<TagEntity, UUID> {
}
