package com.jmag.projet.domain.ocr.service;

import com.jmag.projet.domain.ocr.model.DataConfiguration;
import com.jmag.projet.domain.ocr.model.Document;
import com.jmag.projet.domain.ocr.model.OcrInformations;
import com.jmag.projet.domain.ocr.model.Utilisateur;

import java.util.Set;

public interface DocumentService {

    Set<Document> findDocumentByUtilisateur(Utilisateur utilisateur);

    Set<Document> findDocumentByType(String typeDocument);

    Set<Document> findDocumentByConfiguration(DataConfiguration dataConfiguration);

    Document findDocumentById(String idDocument);

    Document saveDocument(DataConfiguration dataConfiguration,
                          OcrInformations ocrInformations,
                          String typeDocument);

    void deleteDocumentById(String idDocument);

    void deleteDocument(Document document);

    String getIdDocument(Document document);
}
