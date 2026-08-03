package com.echo.translator.model.report;


import java.time.LocalDateTime;
import java.util.List;



public class TranslationReport {


    private final LocalDateTime createdAt;


    private final List<TemplateTranslationReport> templates;



    public TranslationReport(
            LocalDateTime createdAt,
            List<TemplateTranslationReport> templates) {

        this.createdAt = createdAt;
        this.templates = templates;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }



    public List<TemplateTranslationReport> getTemplates() {
        return templates;
    }
}