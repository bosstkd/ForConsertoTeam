package com.jmag.projet.infrastructure.persistance;

import com.jmag.projet.domain.ocr.model.DataConfiguration;
import com.jmag.projet.domain.ocr.service.DataConfigurationService;
import com.jmag.projet.infrastructure.persistance.mongodb.repository.DataConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DataConfigurationServiceImpl implements DataConfigurationService {

    private final DataConfigurationRepository repository;

    @Override
    public Set<DataConfiguration> findAllUtilisateurConfiguration(String idUtilisateur) {
        return repository.findAllUtilisateurConfiguration(idUtilisateur);
    }

    @Override
    public DataConfiguration findConfiguration(String idUtilisateur, String libelleConfiguration) {
        return repository.findConfiguration(idUtilisateur, libelleConfiguration).orElse(null);
    }

    @Override
    public DataConfiguration saveConfiguration(DataConfiguration dataConfiguration) {
        this.getIdConfiguration(dataConfiguration);
        return repository.save(dataConfiguration);
    }

    @Override
    public void deleteConfiguration(DataConfiguration dataConfiguration) {
        this.getIdConfiguration(dataConfiguration);
        repository.delete(dataConfiguration);
    }

    @Override
    public String getIdConfiguration(DataConfiguration dataConfiguration) {
        return StringUtils.isBlank(dataConfiguration.getIdConfiguration()) ? "" +
                dataConfiguration.getIdUtilisateur().hashCode() + dataConfiguration.getLibelleConfiguration().hashCode() :
                dataConfiguration.getIdConfiguration();
    }
}
