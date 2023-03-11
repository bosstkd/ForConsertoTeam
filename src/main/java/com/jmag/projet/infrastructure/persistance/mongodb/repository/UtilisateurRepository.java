package com.jmag.projet.infrastructure.persistance.mongodb.repository;

import com.jmag.projet.domain.ocr.model.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur, String> {
}
