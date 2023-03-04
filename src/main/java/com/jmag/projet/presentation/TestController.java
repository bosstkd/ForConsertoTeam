package com.jmag.projet.presentation;

import com.jmag.projet.application.NumbersService;
import com.jmag.projet.application.OcrService;
import com.jmag.projet.application.PlanerTreeService;
import com.jmag.projet.domain.test.PlanerTree;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController("/")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TestController {

    private final PlanerTreeService planerTreeService;
    private final NumbersService numbersService;

    private final OcrService ocrService;

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
        var inputStream = file.getInputStream();

        return ocrService.getStringFromFile(inputStream);
    }
}
