package com.echo.translator.service.translation;


import java.util.List;

import org.springframework.stereotype.Service;

import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.translation.TranslationTask;


@Service
public class TranslationTaskBuilderService {


    private final TranslationCacheService cacheService;



    public TranslationTaskBuilderService(
            TranslationCacheService cacheService) {

        this.cacheService = cacheService;
    }



    public List<TranslationTask> build(
            TemplateTranslationContext context) {


        return context.getSupportedLanguages()
                .stream()
                .filter(language ->
                        !cacheService.alreadyTranslated(
                                context,
                                language
                        )
                )
                .map(language ->
                        new TranslationTask(
                                context.getMetadata()
                                       .getTemplateName(),

                                context.getMetadata()
                                       .getEnglishTemplatePath(),

                                context.getMetadata()
                                       .getMetadataJsonPath(),

                                language
                        )
                )
                .toList();
    }
}