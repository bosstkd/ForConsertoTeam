package com.jmag.projet.domain.ocr.service;

import com.jmag.projet.domain.ocr.model.Document;

import java.util.Set;

public interface DocumentService {

    Set<Document> findDocumentByIdUtilisateur(String idUtilisateur);
    Set<Document> findDocumentByType(String typeDocument);
    Set<Document> findDocumentByLibelleConfigurationAndIdUtilisateur(String idUtilisateur, String libelleConfiguration);
    Document findDocumentById(String idDocument);
    Document addDocument(Document document);
    void deleteDocumentById(String idDocument);
}
