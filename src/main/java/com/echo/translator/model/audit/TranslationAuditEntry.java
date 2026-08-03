package com.echo.translator.model.audit;


import java.time.LocalDateTime;



public class TranslationAuditEntry {


    private final String templateName;

    private final String language;

    private final String status;

    private final LocalDateTime translatedAt;



    public TranslationAuditEntry(
            String templateName,
            String language,
            String status,
            LocalDateTime translatedAt) {

        this.templateName = templateName;
        this.language = language;
        this.status = status;
        this.translatedAt = translatedAt;
    }



    public String getTemplateName() {
        return templateName;
    }



    public String getLanguage() {
        return language;
    }



    public String getStatus() {
        return status;
    }



    public LocalDateTime getTranslatedAt() {
        return translatedAt;
    }
}