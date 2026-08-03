package com.echo.translator.resolver;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.json.LanguageMetadata;
import com.echo.translator.model.json.TemplateJsonMetadata;
import com.echo.translator.model.metadata.TemplateMetadata;
import com.echo.translator.translation.LanguageGapAnalyzerService;
import com.echo.translator.translation.TranslationFileScanner;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class TemplateMetadataResolverService 
        implements TemplateMetadataResolver {


    private final ObjectMapper objectMapper;

    private final TranslationFileScanner translationFileScanner;

    private final LanguageGapAnalyzerService languageGapAnalyzerService;



    public TemplateMetadataResolverService(
            ObjectMapper objectMapper,
            TranslationFileScanner translationFileScanner,
            LanguageGapAnalyzerService languageGapAnalyzerService) {

        this.objectMapper = objectMapper;
        this.translationFileScanner = translationFileScanner;
        this.languageGapAnalyzerService = languageGapAnalyzerService;
    }



    @Override
    public TemplateTranslationContext resolve(
            TemplateMetadata metadata) {


        Path jsonPath = metadata.getMetadataJsonPath();


        boolean jsonExists = Files.exists(jsonPath);



        if (!jsonExists) {

            return new TemplateTranslationContext(
                    metadata,
                    false,
                    List.of(),
                    List.of(),
                    List.of()
            );
        }



        try {


            TemplateJsonMetadata jsonMetadata =
                    readJson(jsonPath);



            /*
             * JSON tells:
             * Which languages are required
             */
            List<String> supportedLanguages =
                    extractSupportedLanguages(jsonMetadata);



            /*
             * Folder tells:
             * Which translation files already exist
             */
            List<String> existingLanguages =
                    translationFileScanner.scan(
                            metadata.getEnglishTemplatePath()
                                    .getParent()
                    );



            /*
             * Difference:
             * Required - Existing
             */
            List<String> missingLanguages =
                    languageGapAnalyzerService
                            .findMissingLanguages(
                                    supportedLanguages,
                                    existingLanguages
                            );



            return new TemplateTranslationContext(
                    metadata,
                    true,
                    supportedLanguages,
                    existingLanguages,
                    missingLanguages
            );



        } catch (IOException e) {


            throw new IllegalStateException(
                    "Unable to resolve template metadata: "
                    + jsonPath,
                    e
            );
        }
    }




    private TemplateJsonMetadata readJson(
            Path jsonPath) throws IOException {


        return objectMapper.readValue(
                jsonPath.toFile(),
                TemplateJsonMetadata.class
        );
    }




    private List<String> extractSupportedLanguages(
            TemplateJsonMetadata jsonMetadata) {


        if (jsonMetadata.getLanguages() == null) {

            return List.of();
        }


        return jsonMetadata.getLanguages()
                .stream()
                .map(LanguageMetadata::getLanguageCode)
                .toList();
    }
}