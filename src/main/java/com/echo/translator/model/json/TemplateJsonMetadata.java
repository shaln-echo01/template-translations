package com.echo.translator.model.json;

import java.util.List;


public class TemplateJsonMetadata {

    private String templateName;

    private String templateType;

    private List<LanguageMetadata> languages;


    public String getTemplateName() {
        return templateName;
    }


    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }


    public String getTemplateType() {
        return templateType;
    }


    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }


    public List<LanguageMetadata> getLanguages() {
        return languages;
    }


    public void setLanguages(List<LanguageMetadata> languages) {
        this.languages = languages;
    }
}