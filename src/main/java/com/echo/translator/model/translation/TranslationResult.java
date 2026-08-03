package com.echo.translator.model.translation;


public class TranslationResult {


    private final TranslationTask task;

    private final String translatedContent;



    public TranslationResult(
            TranslationTask task,
            String translatedContent) {

        this.task = task;
        this.translatedContent = translatedContent;
    }



    public TranslationTask getTask() {
        return task;
    }



    public String getTranslatedContent() {
        return translatedContent;
    }



    @Override
    public String toString() {

        return "TranslationResult{" +
                "task=" + task +
                ", translatedContentLength="
                + translatedContent.length() +
                '}';
    }
}