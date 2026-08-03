package com.echo.translator.service.translation;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import com.echo.translator.model.json.TemplateJsonMetadata;
import com.echo.translator.model.translation.TranslationResult;
import com.echo.translator.model.translation.TranslationTask;
import com.fasterxml.jackson.databind.ObjectMapper;


class MetadataUpdaterServiceTest {


    private final ObjectMapper objectMapper =
            new ObjectMapper();



    @Test
    void shouldAddNewLanguageToMetadata()
            throws Exception {


        Path metadataFile =
                Files.createTempFile(
                        "template",
                        ".json"
                );


        Files.writeString(
                metadataFile,
                """
                {
                  "templateName":"TestTemplate",
                  "languages":[
                    {
                      "languageCode":"en",
                      "subject":"English"
                    }
                  ]
                }
                """
        );



        TranslationTask task =
                new TranslationTask(
                        "TestTemplate",
                        Path.of(
                           "template_en.mjml"
                        ),
                        metadataFile,
                        "vi"
                );



        TranslationResult result =
                new TranslationResult(
                        task,
                        "Translated MJML Content"
                );



        MetadataUpdaterService service =
                new MetadataUpdaterService(
                        objectMapper
                );



        service.update(result);



        TemplateJsonMetadata metadata =
                objectMapper.readValue(
                        metadataFile.toFile(),
                        TemplateJsonMetadata.class
                );



        assertEquals(
                2,
                metadata.getLanguages()
                        .size()
        );
    }



    @Test
    void shouldNotAddDuplicateLanguage()
            throws Exception {


        Path metadataFile =
                Files.createTempFile(
                        "template",
                        ".json"
                );



        Files.writeString(
                metadataFile,
                """
                {
                  "templateName":"TestTemplate",
                  "languages":[
                    {
                      "languageCode":"vi",
                      "subject":"Vietnamese"
                    }
                  ]
                }
                """
        );



        TranslationTask task =
                new TranslationTask(
                        "TestTemplate",
                        Path.of(
                           "template_en.mjml"
                        ),
                        metadataFile,
                        "vi"
                );



        TranslationResult result =
                new TranslationResult(
                        task,
                        "Translated MJML Content"
                );



        MetadataUpdaterService service =
                new MetadataUpdaterService(
                        objectMapper
                );



        service.update(result);



        TemplateJsonMetadata metadata =
                objectMapper.readValue(
                        metadataFile.toFile(),
                        TemplateJsonMetadata.class
                );



        assertEquals(
                1,
                metadata.getLanguages()
                        .size()
        );
    }
}