package com.jmag.projet.presentation;

import com.jmag.projet.application.NumbersService;
import com.jmag.projet.application.PlanerTreeService;
import com.jmag.projet.application.ocr.ClassificationService;
import com.jmag.projet.application.ocr.OcrService;
import com.jmag.projet.domain.test.PlanerTree;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController("/")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TestController {

    private final PlanerTreeService planerTreeService;
    private final NumbersService numbersService;
    private final OcrService ocrService;
    private final ClassificationService classificationService;

    @PostMapping("/addPlanerTree")
    public PlanerTree addPlanerTree(@RequestBody PlanerTree child) {
        return planerTreeService.addChild(child);
    }

    @GetMapping("/getRomanNumber")
    public String getRomanNumber(@RequestParam("number") String number) {
        return numbersService.getRomanFromArabicNumber(number);
    }

    @PostMapping("/getString")
    public String getString(@RequestParam("file") MultipartFile file) throws IOException, TesseractException, URISyntaxException {

        return ocrService.getStringFromFile(file.getInputStream(), getExtension(file));
    }

    @SneakyThrows
    @PostMapping("/getWordsClass")
    public Map<String, List<String>> getWordsClassification(@RequestParam("file") MultipartFile file) {

        return classificationService.getWordsByClass(file.getInputStream(), getExtension(file));
    }

    @SneakyThrows
    @PostMapping("/getPhrasesClass")
    public Map<String, List<String>> getPhrasesClassification(@RequestParam("file") MultipartFile file) {

        return classificationService.getPhrasesByClass(file.getInputStream(), getExtension(file));
    }

    private String getExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
