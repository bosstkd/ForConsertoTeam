package com.jmag.projet.application.ocr;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClassificationService {

    private final OcrService ocrService;

    @SneakyThrows
    public Map<String, List<String>> getWordsByClass(InputStream inputStream, String extension) {
        String text = ocrService.getStringFromFile(inputStream, extension);
        var words = getWords(text);
        Map<String, List<String>> mapClass = new HashMap<>();
        List<String> arWords = new ArrayList<>();
        List<String> frWords = new ArrayList<>();
        words.forEach(str -> {
            if (textContainsArabic(str)) {
                arWords.add(str);
                mapClass.put("AR", arWords);
            } else {
                frWords.add(str);
                mapClass.put("FR", frWords);
            }
        });
        return mapClass;
    }

    @SneakyThrows
    public Map<String, List<String>> getPhrasesByClass(InputStream inputStream, String extension) {
        String text = ocrService.getStringFromFile(inputStream, extension);
        var words = getPhrases(text);
        Map<String, List<String>> mapClass = new HashMap<>();
        List<String> arWords = new ArrayList<>();
        List<String> frWords = new ArrayList<>();
        words.forEach(str -> {
            if (textContainsArabic(str)) {
                arWords.add(str);
                mapClass.put("AR", arWords);
            } else {
                frWords.add(str);
                mapClass.put("FR", frWords);
            }
        });
        return mapClass;
    }

    private List<String> getWords(String phrase) {

        String syntheticPhrase = phrase.replace("\n", " ")
                .trim()
                .replaceAll(" +", " ");

        return Arrays.stream(syntheticPhrase.split(" "))
                .filter(str -> str.length() > 1)
                .collect(Collectors.toList());
    }

    private List<String> getPhrases(String text) {

        String syntheticText = text
                .replaceAll("\n+", "\n")
                .replaceAll(" +", " ")
                .trim();

        return Arrays.stream(syntheticText.split("\n"))
                .filter(str -> str.length() > 2)
                .collect(Collectors.toList());
    }

    public boolean textContainsArabic(String text) {
        for (char charac : text.toCharArray()) {
            if (Character.UnicodeBlock.of(charac) == Character.UnicodeBlock.ARABIC) {
                return true;
            }
        }
        return false;
    }
}
