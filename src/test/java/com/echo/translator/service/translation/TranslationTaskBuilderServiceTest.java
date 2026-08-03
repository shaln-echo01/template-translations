package com.echo.translator.service.translation;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.metadata.TemplateMetadata;
import com.echo.translator.model.translation.TranslationTask;
import static org.mockito.Mockito.mock;

import com.echo.translator.service.translation.TranslationCacheService;

class TranslationTaskBuilderServiceTest {


    @Test
    void shouldCreateTasksForMissingLanguages() {


        TemplateMetadata metadata =
                new TemplateMetadata(
                        "CasePresentationApproved",
                        Path.of(
                            "CasePresentationApproved_en.mjml"
                        ),
                        Path.of(
                            "CasePresentationApproved.json"
                        )
                );


        TemplateTranslationContext context =
                new TemplateTranslationContext(
                        metadata,
                        true,
                        List.of(
                                "en",
                                "es",
                                "fr",
                                "vi"
                        ),
                        List.of(
                                "en",
                                "es"
                        ),
                        List.of(
                                "fr",
                                "vi"
                        )
                );


        TranslationCacheService cacheService =
                mock(
                    TranslationCacheService.class
                );


        TranslationTaskBuilderService service =
                new TranslationTaskBuilderService(
                        cacheService
                );



        List<TranslationTask> tasks =
                service.build(context);



        assertEquals(
                2,
                tasks.size()
        );


        assertEquals(
                "fr",
                tasks.get(0)
                     .getTargetLanguage()
        );


        assertEquals(
                "vi",
                tasks.get(1)
                     .getTargetLanguage()
        );
    }



    @Test
    void shouldReturnEmptyListWhenNoMissingLanguages() {


        TemplateMetadata metadata =
                new TemplateMetadata(
                        "TestTemplate",
                        Path.of("template_en.mjml"),
                        Path.of("template.json")
                );


        TemplateTranslationContext context =
                new TemplateTranslationContext(
                        metadata,
                        true,
                        List.of(
                                "en",
                                "es"
                        ),
                        List.of(
                                "en",
                                "es"
                        ),
                        List.of()
                );



        TranslationCacheService cacheService =
                mock(
                    TranslationCacheService.class
                );


        TranslationTaskBuilderService service =
                new TranslationTaskBuilderService(
                        cacheService
                );



        List<TranslationTask> tasks =
                service.build(context);



        assertTrue(
                tasks.isEmpty()
        );
    }
}