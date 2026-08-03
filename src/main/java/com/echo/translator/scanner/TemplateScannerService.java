package com.echo.translator.scanner;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.echo.translator.config.TranslatorProperties;
import com.echo.translator.model.metadata.TemplateMetadata;



@Service
public class TemplateScannerService
        implements TemplateScanner {



    private final TranslatorProperties translatorProperties;



    public TemplateScannerService(
            TranslatorProperties translatorProperties) {

        this.translatorProperties =
                translatorProperties;
    }



    @Override
    public List<TemplateMetadata> scan() throws IOException {


        Path templateRoot =
                translatorProperties.getTemplateDirectory();


        if (!Files.exists(templateRoot)) {

            throw new IllegalStateException(
                    "Template directory not found: "
                    + templateRoot
            );
        }


        try (Stream<Path> paths = Files.walk(templateRoot)) {

            return paths
                    .filter(Files::isRegularFile)
                    .filter(path ->
                        path.toString()
                             .endsWith("_en.mjml")
                    )
                    .map(this::createMetadata)
                    .toList();
        }
    }



    private TemplateMetadata createMetadata(
            Path englishTemplatePath) {


        String fileName =
                englishTemplatePath
                .getFileName()
                .toString();


        String templateName =
                fileName.replace(
                        "_en.mjml",
                        ""
                );


        Path metadataJsonPath =
                englishTemplatePath
                        .getParent()
                        .resolve(
                            templateName + ".json"
                        );


        return new TemplateMetadata(
                templateName,
                englishTemplatePath,
                metadataJsonPath
        );
    }
}