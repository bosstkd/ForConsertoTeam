package com.jmag.projet.infrastructure.ocr.configuration;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.nio.file.Paths;

@Configuration
public class OcrConfiguration {

    private static String FILE_SOURCE_PATH = "tessFiles/markFile.tkt";

    @Bean
    public Tesseract tesseract (){
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath(getTessDataPath("tessdata"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        tesseract.setLanguage("fra+ara");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);

        return tesseract;
    }

    private String getTessDataPath(String folderName) throws URISyntaxException {
        String tessdataPath = Paths.get(ClassLoader.getSystemResource(FILE_SOURCE_PATH).toURI()).toUri().getPath();
        return tessdataPath.substring(1, tessdataPath.indexOf("target")) + folderName;
    }
}
