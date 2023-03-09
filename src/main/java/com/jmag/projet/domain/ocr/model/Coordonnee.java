package com.jmag.projet.domain.ocr.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.jmag.projet.domain.ocr.common.InternalRegex.TELEPHONE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Coordonnee {

    private String adresse;

    @Pattern(regexp = TELEPHONE, message = "le format téléphone n'est pas valide !")
    private String telephone;

    @NotBlank(message = "l'adresse mail ne peut être vide !")
    @Email(message = "le format de l'adresse mail n'est pas valide !")
    private String mail;
}
