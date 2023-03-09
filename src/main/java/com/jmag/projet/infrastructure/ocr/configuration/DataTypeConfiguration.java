package com.jmag.projet.infrastructure.ocr.configuration;

import com.jmag.projet.domain.ocr.common.DatasTypes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataTypeConfiguration {

    private final String VED = "(\\s|\\-){0,1}"; //VIDE_ESPACE_DASH
    private final String DD = "\\d{2}";//DEUX_DIGITS
    private final String TD = "\\d{3}";//TROIS_DIGITS
    private final String QD = "\\d{4}";//QUATRE_DIGITS
    private final String CD = "\\d{5}";//CINQ_DIGITS
    private final String SP = "\\s";//ESPACE

    public final String DATE = "(0?[1-9]|[12][0-9]|3[01])(\\/|-)(0?[1-9]|1[012])(\\/|-)((19|20)\\d{2})";
    public final String EMAIL = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    public final String RIB = "^([A-Z]{2}" + CD + ")(" + CD + ")([A-Z\\d]{11})(" + DD + ")$";
    public final String IBAN = "[A-Z]{2}" + DD + VED + QD + VED + QD + VED + QD + VED + QD + VED + QD + VED + TD;
    public final String SSN = "(2|1|3|7){1}" + VED + DD + VED + DD + VED + DD + VED + TD + VED + TD + "(" + VED + DD + "){0,1}";
    public final String FRANCE_ID_NUMBER = "^(0[1-9]|[12]\\d|3[01])(0[1-9]|1[0-2])(\\d{3})(\\d{3})(\\d{2})$";
    public final String CHIFFRES = "([0-9]*)";
    public final String SIRET = "[0-9]{14}";
    public final String CODE_POSTAL = "([0-9]{5})";
    public final String TELEPHONE = "^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$";
    private final String SANS_CHARS_SPECIAUX = "^[a-zA-ZàâäáãçéèêëîïìíôöòóõùûüúÿýæœñÀÂÄÁÃÇÉÈÊËÎÏÌÍÔÖÒÓÕÙÛÜŸÝÆŒÑ' .-]";
    public final String NOM_SANS_CHARS_SPECIAUX = SANS_CHARS_SPECIAUX + "+$";
    public final String RAISON_SOCIALE = "^\\s*(?:\\S\\s*){0,50}$";
    public final String BIC = SP + "^[a-z]{6}[0-9a-z]{2}([0-9a-z]{3})?\\z";
    public final String SOMME = "(([1-9]{1,3}|0{1}))((" + SP + "|\\-){0,1}(" + SP + "|" + TD + ")){0,1}(\\.|,)" + DD;

    @Bean
    public DatasTypes getDatasTypes() throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();

        var fields = this.getClass().getFields();
        for (var field : fields) {
            if (field.getType().getCanonicalName().equals("java.lang.String")) {
                map.put(field.getName(), (String) field.get(this));
                System.out.println(field.getName() + " : " + field.get(this));
            }
        }
        return DatasTypes.builder()
                .map(map)
                .build();
    }
}
