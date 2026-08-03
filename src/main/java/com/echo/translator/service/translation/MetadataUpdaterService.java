package com.echo.translator.service.translation;


import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import com.echo.translator.model.translation.TranslationResult;
import com.echo.translator.model.translation.TranslationTask;
import com.echo.translator.model.json.TemplateJsonMetadata;
import com.echo.translator.model.json.LanguageMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class MetadataUpdaterService
        implements MetadataUpdater {


    private final ObjectMapper objectMapper;


    public MetadataUpdaterService(
            ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }



    @Override
    public void update(
            TranslationResult result)
            throws IOException {


        TranslationTask task =
                result.getTask();


        Path metadataPath =
                task.getMetadataJsonPath();


        TemplateJsonMetadata metadata =
                objectMapper.readValue(
                        metadataPath.toFile(),
                        TemplateJsonMetadata.class
                );


        boolean alreadyExists =
                metadata.getLanguages()
                        .stream()
                        .anyMatch(language ->
                                language.getLanguageCode()
                                .equals(
                                    task.getTargetLanguage()
                                )
                        );


        if (!alreadyExists) {


        	metadata.getLanguages()
            .add(
                new LanguageMetadata(
                        task.getTargetLanguage()
                )
            );
        }


        objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(
                        metadataPath.toFile(),
                        metadata
                );
    }
}