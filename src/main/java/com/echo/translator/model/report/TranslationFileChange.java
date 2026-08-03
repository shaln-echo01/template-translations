package com.echo.translator.model.report;


public class TranslationFileChange {


    private final String fileName;

    private final String action;



    public TranslationFileChange(
            String fileName,
            String action) {

        this.fileName = fileName;
        this.action = action;
    }



    public String getFileName() {
        return fileName;
    }



    public String getAction() {
        return action;
    }



    @Override
    public String toString() {

        return fileName + " : " + action;
    }
}