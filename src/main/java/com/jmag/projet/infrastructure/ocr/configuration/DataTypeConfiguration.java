package com.jmag.projet.infrastructure.ocr.configuration;

import com.jmag.projet.domain.model.DatasTypes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataTypeConfiguration {

    public final String DATE = "\\b(0?[1-9]|[12][0-9]|3[01])(\\/|-)(0?[1-9]|1[012])(\\/|-)((19|20)\\d{2})\\b";
    public final String EMAIL = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
    public final String RIB = "^([A-Z]{2}\\d{5})(\\d{5})([A-Z\\d]{11})(\\d{2})$";
    public final String IBAN = "[A-Z]{2}\\d{2}(\\s|\\-){0,1}\\d{4}(\\s|\\-){0,1}\\d{4}(\\s|\\-){0,1}\\d{4}(\\s|\\-){0,1}\\d{4}(\\s|\\-){0,1}\\d{4}(\\s|\\-){0,1}\\d{3}";
    public final String SSN = "^(2|1|3|7)\\d{2}(?:\\d{2}){2}\\d{3}(?:\\d{2})?$";
    public final String FRANCE_ID_NUMBER = "^(0[1-9]|[12]\\d|3[01])(0[1-9]|1[0-2])(\\d{3})(\\d{3})(\\d{2})$";
    public final String CHIFFRES = "([0-9]*)";
    public final String SIRET = "[0-9]{14}";
    public final String CODE_POSTAL = "([0-9]{5})";
    public final String TELEPHONE = "[0-9]{10}";
    public final String TELEPHONE_FIXE = "[0-9]{10}";
    public final String TELEPHONE_PORTABLE = "^0[6-7][0-9]{8}";
    private final String SANS_CHARS_SPECIAUX = "^[a-zA-ZàâäáãçéèêëîïìíôöòóõùûüúÿýæœñÀÂÄÁÃÇÉÈÊËÎÏÌÍÔÖÒÓÕÙÛÜŸÝÆŒÑ' .-]";
    public final String NOM_SANS_CHARS_SPECIAUX = SANS_CHARS_SPECIAUX + "+$";
    public final String RAISON_SOCIALE = "^\\s*(?:\\S\\s*){0,50}$";
    public final String BIC = "[A-Z]{6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3})?";

    @Bean
    public DatasTypes getDatasTypes() throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();

        var fields = this.getClass().getFields();
        for (var field : fields) {
            if (field.getType().getCanonicalName().equals("java.lang.String")) {
                map.put(field.getName(), (String) field.get(this));
            }
        }
        return DatasTypes.builder()
                .map(map)
                .build();
    }
}
