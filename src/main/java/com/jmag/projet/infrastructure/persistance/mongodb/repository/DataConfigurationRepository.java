package com.jmag.projet.infrastructure.persistance.mongodb.repository;

import com.jmag.projet.domain.ocr.model.DataConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface DataConfigurationRepository extends MongoRepository<DataConfiguration, String> {

    @Query("{'idUtilisateur' : ?0}")
    Set<DataConfiguration> findAllUtilisateurConfiguration(String idUtilisateur);

    @Query("{'idUtilisateur' : ?0, 'libelleConfiguration' : ?1}")
    Optional<DataConfiguration> findConfiguration(String idUtilisateur, String libelleConfiguration);
}
