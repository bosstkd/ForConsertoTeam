package com.jmag.projet.application.ocr;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OcrService {

    public static final int DEFAULT_BUFFER_SIZE = 8192;
    private static String FILE_SOURCE_PATH = "tessFiles/markFile.tkt";

    public String getStringFromFile(InputStream inputStream, String extension)
            throws TesseractException, IOException, URISyntaxException {


        File file = new File(getTessDataPath("tessFiles") + "/" + buildFileName(extension));
        copyInputStreamToFile(inputStream, file);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(getTessDataPath("tessdata"));
        tesseract.setLanguage("fra+ara");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        var result = tesseract.doOCR(file);
        file.delete();
        return result;
    }

    private String getTessDataPath(String folderName) throws URISyntaxException {
        String tessdataPath = Paths.get(ClassLoader.getSystemResource(FILE_SOURCE_PATH).toURI()).toUri().getPath();
        return tessdataPath.substring(1, tessdataPath.indexOf("target")) + folderName;
    }

    private void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }

    private String buildFileName(final String extension) {

        return System.currentTimeMillis() + extension;
    }
}
