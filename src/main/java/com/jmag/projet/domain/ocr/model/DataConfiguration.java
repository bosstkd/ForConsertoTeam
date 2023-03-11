package com.jmag.projet.domain.ocr.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DataConfiguration {

    @Id
    private String idConfiguration;

    @NotBlank(message = "l'id utilisateur ne peut être null !")
    private String idUtilisateur;

    @NotBlank(message = "le libelle de configuration ne peut être null !")
    private String libelleConfiguration;

    @NotNull(message = "la configuration ne peut être null !")
    private Map<String, String> configuration;
}
