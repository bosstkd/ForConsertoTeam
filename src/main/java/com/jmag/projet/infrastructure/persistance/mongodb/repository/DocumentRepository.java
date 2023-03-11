package com.jmag.projet.infrastructure.persistance.mongodb.repository;

import com.jmag.projet.domain.ocr.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DocumentRepository extends MongoRepository<Document, String> {

    @Query("{'extractionConfig.idConfiguration' : ?0}")
    Set<Document> findDocumentByIdConfiguration(String idConfiguration);

    @Query("{'extractionConfig.idUtilisateur' : ?0}")
    Set<Document> findDocumentByIdUtilisateur(String idUtilisateur);

    @Query("{'typeDocument' : ?0}")
    Set<Document> findDocumentByType(String typeDocument);
}
