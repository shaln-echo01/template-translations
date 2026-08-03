package com.echo.translator.service.translation;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.nio.file.Path;
import java.util.List;


import org.junit.jupiter.api.Test;


import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.metadata.TemplateMetadata;



class TranslationCacheServiceTest {



    @Test
    void shouldReturnTrueWhenLanguageAlreadyTranslated() {


        TranslationCacheService service =
                new TranslationCacheService();



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
                                "fr",
                                "es"
                        ),
                        List.of(
                                "en",
                                "fr"
                        ),
                        List.of(
                                "es"
                        )
                );



        boolean result =
                service.alreadyTranslated(
                        context,
                        "fr"
                );



        assertTrue(result);
    }



    @Test
    void shouldReturnFalseWhenLanguageIsMissing() {


        TranslationCacheService service =
                new TranslationCacheService();



        TemplateMetadata metadata =
                new TemplateMetadata(
                        "TestTemplate",
                        Path.of(
                            "template_en.mjml"
                        ),
                        Path.of(
                            "template.json"
                        )
                );



        TemplateTranslationContext context =
                new TemplateTranslationContext(
                        metadata,
                        true,
                        List.of(
                                "en",
                                "fr"
                        ),
                        List.of(
                                "en"
                        ),
                        List.of(
                                "fr"
                        )
                );



        boolean result =
                service.alreadyTranslated(
                        context,
                        "fr"
                );



        assertFalse(result);
    }
}