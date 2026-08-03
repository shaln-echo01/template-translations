package com.echo.translator.service.ai;


public interface GeminiPromptBuilder {


    String build(
            String mjmlContent,
            String targetLanguage
    );

}