package com.ukeess.utils;

import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Nazar Lelyak.
 */
@UtilityClass
public class FileUtils {
    public String readFromFile(String fileName) throws IOException {
        String filePath = String.format("responces/%s", fileName);
        File resource = new ClassPathResource(filePath).getFile();
        byte[] byteArray = Files.readAllBytes(resource.toPath());
        return new String(byteArray);
    }
}
