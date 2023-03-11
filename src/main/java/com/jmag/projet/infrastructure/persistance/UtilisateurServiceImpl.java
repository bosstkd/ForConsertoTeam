package com.jmag.projet.infrastructure.persistance;

import com.jmag.projet.domain.ocr.model.Utilisateur;
import com.jmag.projet.domain.ocr.service.UtilisateurService;
import com.jmag.projet.infrastructure.persistance.mongodb.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository repository;

    @Override
    public Utilisateur findUtilisateurById(String idUtilisateur) {

        return repository.findById(idUtilisateur).orElse(null);
    }

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {

        getIdUtilisateur(utilisateur);
        return repository.save(utilisateur);
    }

    @Override
    public String getIdUtilisateur(Utilisateur utilisateur) {

        return StringUtils.isBlank(utilisateur.getIdUtilisateur()) ?
                String.valueOf(utilisateur.getCoordonnee().hashCode()) :
                utilisateur.getIdUtilisateur();
    }
}
