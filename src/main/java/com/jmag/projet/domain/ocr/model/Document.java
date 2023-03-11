package com.jmag.projet.domain.ocr.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Document {

    @Id
    private String idDocument;
    private LocalDateTime dateExtraction;
    private String typeDocument;
    private DataConfiguration extractionConfig;
    private OcrInformations ocrInformations;
}
