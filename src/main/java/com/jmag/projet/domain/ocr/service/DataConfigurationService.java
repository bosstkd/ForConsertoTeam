package com.jmag.projet.domain.ocr.service;

import com.jmag.projet.domain.ocr.model.DataConfiguration;

import java.util.Set;

public interface DataConfigurationService {

    Set<DataConfiguration> findAllUtilisateurConfiguration(String idUtilisateur);
    DataConfiguration findConfiguration(String idUtilisateur, String libelleConfiguration);
    DataConfiguration saveConfiguration(DataConfiguration dataConfiguration);
    void deleteConfiguration(DataConfiguration dataConfiguration);
    String getIdConfiguration(DataConfiguration dataConfiguration);
}
