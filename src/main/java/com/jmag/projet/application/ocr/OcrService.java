package com.jmag.projet.application.ocr;

import com.jmag.projet.domain.exceptions.PlanerTreeBadRequestException;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OcrService {

    private final Tesseract tesseract;

    public static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final String FILE_SOURCE_PATH = "tessFiles/markFile.tkt";
    private static final List<String> IMAGES_FORMATS = List.of(".jpg", ".jpeg", ".png");
    private static final List<String> SUPPORTED_FORMATS = List.of(".jpg", ".jpeg", ".png", ".pdf");

    public String getStringFromFile(InputStream inputStream, String extension)
            throws TesseractException, IOException, URISyntaxException {

        if (!SUPPORTED_FORMATS.contains(extension)) {
            throw new PlanerTreeBadRequestException("Le format '" + extension + "' n'est valable, formats valable: " +
                    SUPPORTED_FORMATS.stream().reduce("", (subTotal, e) -> "'" + e + "' " + subTotal));
        }

        if (IMAGES_FORMATS.contains(extension.toLowerCase())) {
            BufferedImage imBuff = ImageIO.read(inputStream);
            return tesseract.doOCR(imBuff);
        }

        File file = new File(getTessDataPath("tessFiles") + "/" + buildFileName(extension));
        copyInputStreamToFile(inputStream, file);
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
