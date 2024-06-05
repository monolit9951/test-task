package com.IntegrityCheckTeam.JavaTechnicalChallenge.repository;

import com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.document.ItemDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<ItemDocument, String> {
    Page<ItemDocument> findPageItemDocumentBy(Pageable pageable);
}
