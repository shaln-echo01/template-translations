package com.echo.translator.service.translation;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.echo.translator.client.ai.GeminiClient;
import com.echo.translator.model.ai.GeminiTranslationRequest;
import com.echo.translator.model.ai.GeminiTranslationResponse;
import com.echo.translator.service.ai.GeminiPromptBuilderService;
import com.echo.translator.service.ai.GeminiService;
import com.echo.translator.service.ai.GeminiServiceImpl;



class GeminiServiceTest {


    private GeminiService geminiService;



    @BeforeEach
    void setUp() {


        GeminiClient geminiClient =
                mock(
                    GeminiClient.class
                );


        GeminiPromptBuilderService promptBuilder =
                mock(
                    GeminiPromptBuilderService.class
                );


        when(
            promptBuilder.build(
                    anyString(),
                    anyString()
            )
        )
        .thenReturn(
            new com.echo.translator.model.ai.GeminiPrompt(
                    "Translate template"
            )
        );



        when(
            geminiClient.generate(
                    anyString()
            )
        )
        .thenReturn(
            "Translated MJML"
        );



        geminiService =
                new GeminiServiceImpl(
                        geminiClient,
                        promptBuilder
                );
    }



    @Test
    void shouldTranslateTemplate() {


        GeminiTranslationRequest request =
                new GeminiTranslationRequest(
                        "<mjml>Hello</mjml>",
                        "fr"
                );


        GeminiTranslationResponse response =
                geminiService.translate(request);



        assertNotNull(
                response
        );
    }
}