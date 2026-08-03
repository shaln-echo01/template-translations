package com.echo.translator.model.report;


import java.util.List;



public class TemplateTranslationReport {


    private final String templateName;


    private final List<String> translatedLanguages;


    private final List<TranslationFileChange> fileChanges;



    public TemplateTranslationReport(
            String templateName,
            List<String> translatedLanguages,
            List<TranslationFileChange> fileChanges) {

        this.templateName = templateName;
        this.translatedLanguages = translatedLanguages;
        this.fileChanges = fileChanges;
    }



    public String getTemplateName() {
        return templateName;
    }



    public List<String> getTranslatedLanguages() {
        return translatedLanguages;
    }



    public List<TranslationFileChange> getFileChanges() {
        return fileChanges;
    }
}