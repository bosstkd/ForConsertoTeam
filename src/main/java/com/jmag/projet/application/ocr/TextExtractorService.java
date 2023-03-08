package com.jmag.projet.application.ocr;

import com.jmag.projet.domain.model.DataExtractionModel;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TextExtractorService {

    public Set<DataExtractionModel> extractMatchingString(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        Set<DataExtractionModel> listMatcherWords = new HashSet<>();
        while (matcher.find()) {
            if (!matcher.group().isBlank()) {
                listMatcherWords.add(DataExtractionModel.builder()
                                .data(matcher.group())
                                .source(text)
                        .build());
            }
            String testText = text.substring(matcher.end());
            if (testText.equals(text)) {
                break;
            }
            text = testText;
            matcher = pattern.matcher(text);
        }

        return listMatcherWords;
    }
}
