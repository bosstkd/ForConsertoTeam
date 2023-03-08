package com.jmag.projet.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DataExtractionModel {

    private String data;
    private String source;
}
