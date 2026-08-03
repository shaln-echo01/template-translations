package com.echo.translator.model.translation;


import java.nio.file.Path;


public class TranslationTask {


    private final String templateName;

    private final Path sourceTemplatePath;

    private final Path metadataJsonPath;

    private final String targetLanguage;



    public TranslationTask(
            String templateName,
            Path sourceTemplatePath,
            Path metadataJsonPath,
            String targetLanguage) {

        this.templateName = templateName;
        this.sourceTemplatePath = sourceTemplatePath;
        this.metadataJsonPath = metadataJsonPath;
        this.targetLanguage = targetLanguage;
    }



    public String getTemplateName() {
        return templateName;
    }



    public Path getSourceTemplatePath() {
        return sourceTemplatePath;
    }



    public Path getMetadataJsonPath() {
        return metadataJsonPath;
    }



    public String getTargetLanguage() {
        return targetLanguage;
    }



    @Override
    public String toString() {

        return "TranslationTask{" +
                "templateName='" + templateName + '\'' +
                ", sourceTemplatePath=" + sourceTemplatePath +
                ", metadataJsonPath=" + metadataJsonPath +
                ", targetLanguage='" + targetLanguage + '\'' +
                '}';
    }
}