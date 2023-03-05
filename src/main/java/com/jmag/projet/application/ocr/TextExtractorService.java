package com.jmag.projet.application.ocr;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TextExtractorService {

    public List<String> extractMatchingString(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> listMatcherWords = new ArrayList<>();
        while (matcher.find()) {
            if(!matcher.group().isBlank()){
                listMatcherWords.add(matcher.group());
            }
            String testText = text.substring(matcher.end());
            if(testText.equals(text)){
                break;
            }
            text = testText;
            matcher = pattern.matcher(text);
        }

        return listMatcherWords;
    }
}
