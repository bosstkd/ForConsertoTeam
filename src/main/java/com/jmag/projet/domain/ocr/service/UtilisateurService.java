package com.jmag.projet.domain.ocr.service;

import com.jmag.projet.domain.ocr.model.Utilisateur;

public interface UtilisateurService {

    Utilisateur findUtilisateurById(String idUtilisateur);
    Utilisateur saveUtilisateur(Utilisateur utilisateur);
    String getIdUtilisateur(Utilisateur utilisateur);
}
