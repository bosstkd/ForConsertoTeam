package com.jmag.projet.domain.ocr.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Utilisateur {

    @Id
    @NotBlank(message = "l'id utilisateur ne peut être null !")
    private String idUtilisateur;
    @NotBlank(message = "Le nom ou la raison sociale ne peut être vide !")
    private String nomOuRaisonSociale;

    @Valid
    @NotNull(message = "Les coordonnees ne peuvent être null !")
    private Coordonnee coordonnee;

    private String secteurActivite;

    private LocalDateTime dateActivationCompte;
    private Integer periodeActivationCompte;
}
