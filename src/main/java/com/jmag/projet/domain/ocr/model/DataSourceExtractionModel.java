package com.jmag.projet.domain.ocr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DataSourceExtractionModel {

    private String data;
    private String source;
}
