package com.jmag.projet.presentation.ocr;

import com.jmag.projet.application.ocr.ClassificationService;
import com.jmag.projet.application.ocr.OcrService;
import com.jmag.projet.application.ocr.TextExtractorService;
import com.jmag.projet.domain.exceptions.PlanerTreeBadRequestException;
import com.jmag.projet.domain.ocr.model.DataSourceExtractionModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;

@RestController("/ocr")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OcrRessource {

    private final OcrService ocrService;
    private final ClassificationService classificationService;
    private final TextExtractorService textExtractorService;

    @SneakyThrows
    @PostMapping("/to-ocr")
    public String getString(@RequestParam("file") MultipartFile file) {

        return ocrService.getStringFromFile(file.getInputStream(), getExtension(file));
    }

    @SneakyThrows
    @PostMapping("/words-by-language")
    public Map<String, Set<String>> getWordsClassification(@RequestParam("file") MultipartFile file) {

        return classificationService.getWordsByClass(file.getInputStream(), getExtension(file));
    }

    @SneakyThrows
    @PostMapping("/phrases-class")
    public Map<String, Set<DataSourceExtractionModel>> getPhrasesClassification(@RequestParam("file") MultipartFile file) {

        return classificationService.getPhrasesByClass(file.getInputStream(), getExtension(file));
    }

    private String getExtension(MultipartFile file) {
        if (isNull(file)) {
            throw new PlanerTreeBadRequestException("Le fichier envoyé n'accepte pas la valeur null !");
        }
        String fileName = Optional.of(file).map(MultipartFile::getOriginalFilename).orElseThrow(() ->
                new PlanerTreeBadRequestException("Le fichier doit avoir un nom !")
        );
        if (!fileName.contains(".")) {
            throw new PlanerTreeBadRequestException("Le fichier doit avoir une extension !");
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
