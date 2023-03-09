package com.jmag.projet.domain.ocr.service;

import com.jmag.projet.domain.ocr.model.Utilisateur;

public interface UtilsateurService {

    Utilisateur findUtilisateurById(String idUtilisateur);
    Utilisateur AddUtilisateur(Utilisateur utilisateur);
    Utilisateur UpdateUtilisateur(Utilisateur utilisateur);
}
