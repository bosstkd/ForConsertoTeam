package com.jmag.projet.presentation;

import com.jmag.projet.application.NumbersService;
import com.jmag.projet.application.PlanerTreeService;
import com.jmag.projet.application.ocr.ClassificationService;
import com.jmag.projet.application.ocr.OcrService;
import com.jmag.projet.application.ocr.TextExtractorService;
import com.jmag.projet.domain.test.PlanerTree;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TestController {

    private final PlanerTreeService planerTreeService;
    private final NumbersService numbersService;
    private final OcrService ocrService;
    private final ClassificationService classificationService;
    private final TextExtractorService textExtractorService;

    @PostMapping("/addPlanerTree")
    public PlanerTree addPlanerTree(@RequestBody PlanerTree child) {
        return planerTreeService.addChild(child);
    }

    @GetMapping("/getRomanNumber")
    public String getRomanNumber(@RequestParam("number") String number) {
        return numbersService.getRomanFromArabicNumber(number);
    }
}
