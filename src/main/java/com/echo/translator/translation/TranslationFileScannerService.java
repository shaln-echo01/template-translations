package com.echo.translator.translation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class TranslationFileScannerService 
        implements TranslationFileScanner {


    @Override
    public List<String> scan(
            Path templateDirectory) throws IOException {


        return Files.list(templateDirectory)
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString())
                .filter(this::isMjmlFile)
                .map(this::extractLanguage)
                .toList();
    }


    private boolean isMjmlFile(String fileName) {

        return fileName.endsWith(".mjml");
    }


    private String extractLanguage(String fileName) {

        int underscoreIndex =
                fileName.lastIndexOf("_");

        int extensionIndex =
                fileName.lastIndexOf(".");


        return fileName.substring(
                underscoreIndex + 1,
                extensionIndex
        );
    }
}