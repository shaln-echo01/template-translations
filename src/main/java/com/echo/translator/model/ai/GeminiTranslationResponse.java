package com.echo.translator.model.ai;


public class GeminiTranslationResponse {


    private final String translatedContent;


    public GeminiTranslationResponse(
            String translatedContent) {

        this.translatedContent = translatedContent;
    }


    public String getTranslatedContent() {
        return translatedContent;
    }
}