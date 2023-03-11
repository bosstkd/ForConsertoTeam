package com.jmag.projet.infrastructure.persistance;

import com.jmag.projet.domain.ocr.model.DataConfiguration;
import com.jmag.projet.domain.ocr.model.Document;
import com.jmag.projet.domain.ocr.model.OcrInformations;
import com.jmag.projet.domain.ocr.model.Utilisateur;
import com.jmag.projet.domain.ocr.service.DocumentService;
import com.jmag.projet.infrastructure.persistance.mongodb.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;

    @Override
    public Set<Document> findDocumentByUtilisateur(Utilisateur utilisateur) {

        return repository.findDocumentByIdUtilisateur(utilisateur.getIdUtilisateur());
    }

    @Override
    public Set<Document> findDocumentByType(String typeDocument) {

        return repository.findDocumentByType(typeDocument);
    }

    @Override
    public Set<Document> findDocumentByConfiguration(DataConfiguration dataConfiguration) {

        return repository.findDocumentByIdConfiguration(dataConfiguration.getIdConfiguration());
    }

    @Override
    public Document findDocumentById(String idDocument) {

        return repository.findById(idDocument).orElse(null);
    }

    @Override
    public Document saveDocument(DataConfiguration dataConfiguration,
                                 OcrInformations ocrInformations,
                                 String typeDocument) {

        var document = Document.builder()
                .ocrInformations(ocrInformations)
                .dateExtraction(LocalDateTime.now())
                .typeDocument(typeDocument)
                .extractionConfig(dataConfiguration)
                .build();
        getIdDocument(document);
        return document;
    }

    @Override
    public void deleteDocumentById(String idDocument) {

        repository.deleteById(idDocument);
    }

    @Override
    public void deleteDocument(Document document) {

        repository.delete(document);
    }

    @Override
    public String getIdDocument(Document document) {

        return StringUtils.isBlank(document.getIdDocument()) ? "" +
                document.getOcrInformations().hashCode() + document.getExtractionConfig().hashCode() :
                document.getIdDocument();
    }


}
