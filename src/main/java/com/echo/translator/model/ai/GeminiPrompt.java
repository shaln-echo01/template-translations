package com.echo.translator.model.ai;


public class GeminiPrompt {


    private final String content;



    public GeminiPrompt(
            String content) {

        this.content = content;
    }



    public String getContent() {

        return content;
    }
}