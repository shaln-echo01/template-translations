package com.echo.translator.model.ai;


public class GeminiTranslationRequest {


    private final String content;

    private final String targetLanguage;


    public GeminiTranslationRequest(
            String content,
            String targetLanguage) {

        this.content = content;
        this.targetLanguage = targetLanguage;
    }


    public String getContent() {
        return content;
    }


    public String getTargetLanguage() {
        return targetLanguage;
    }
}