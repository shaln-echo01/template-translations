package com.echo.translator.service.ai;


import org.springframework.stereotype.Service;

import com.echo.translator.client.ai.GeminiClient;
import com.echo.translator.model.ai.GeminiPrompt;
import com.echo.translator.model.ai.GeminiTranslationRequest;
import com.echo.translator.model.ai.GeminiTranslationResponse;



@Service
public class GeminiServiceImpl
        implements GeminiService {


    private final GeminiClient geminiClient;
    private final GeminiPromptBuilderService promptBuilder;


    public GeminiServiceImpl(
            GeminiClient geminiClient,
            GeminiPromptBuilderService promptBuilder) {

        this.geminiClient = geminiClient;
        this.promptBuilder = promptBuilder;
    }



    @Override
    public GeminiTranslationResponse translate(
            GeminiTranslationRequest request) {


        GeminiPrompt prompt =
                promptBuilder.build(
                        request.getContent(),
                        request.getTargetLanguage()
                );


        String translatedContent =
                geminiClient.generate(
                        prompt.getContent()
                );


        return new GeminiTranslationResponse(
                translatedContent
        );
    }
}