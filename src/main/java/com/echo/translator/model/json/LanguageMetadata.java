package com.echo.translator.model.json;


public class LanguageMetadata {


    private String languageCode;

    private String subject;


    public LanguageMetadata() {
    }


    public LanguageMetadata(
            String languageCode) {

        this.languageCode = languageCode;
    }


    public LanguageMetadata(
            String languageCode,
            String subject) {

        this.languageCode = languageCode;
        this.subject = subject;
    }


    public String getLanguageCode() {
        return languageCode;
    }


    public void setLanguageCode(
            String languageCode) {

        this.languageCode = languageCode;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(
            String subject) {

        this.subject = subject;
    }
}