package com.echo.translator.client.ai;


import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import com.echo.translator.config.GeminiProperties;
import com.echo.translator.exception.GeminiTranslationException;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;



@Component
public class GeminiClientImpl
        implements GeminiClient {


    private final Client client;

    private final GeminiProperties geminiProperties;



    public GeminiClientImpl(
            GeminiProperties geminiProperties) {

        this.geminiProperties = geminiProperties;


        this.client =
                Client.builder()
                      .apiKey(
                          geminiProperties.getApiKey()
                      )
                      .build();
    }



    @Override
    @Retryable(
        retryFor = GeminiTranslationException.class,
        maxAttempts = 3
    )
    public String generate(
            String prompt) {


        try {

            GenerateContentResponse response =
                    client.models.generateContent(
                            geminiProperties.getModel(),
                            prompt,
                            null
                    );


            String content =
                    response.text();


            if(content == null || content.isBlank()) {

                throw new GeminiTranslationException(
                        "Empty Gemini response"
                );
            }


            return content;


        } catch(Exception e) {


            throw new GeminiTranslationException(
                    "Gemini generation failed",
                    e
            );
        }
    }



    @Recover
    public String recover(
            GeminiTranslationException exception,
            String prompt) {


        throw new GeminiTranslationException(
                "Translation failed after retries",
                exception
        );
    }
}