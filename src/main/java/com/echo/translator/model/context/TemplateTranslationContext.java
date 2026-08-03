package com.echo.translator.model.context;

import java.util.List;

import com.echo.translator.model.metadata.TemplateMetadata;


public class TemplateTranslationContext {


    private final TemplateMetadata metadata;

    private final boolean metadataJsonExists;

    private final List<String> supportedLanguages;

    private final List<String> existingLanguages;

    private final List<String> missingLanguages;



    public TemplateTranslationContext(
            TemplateMetadata metadata,
            boolean metadataJsonExists,
            List<String> supportedLanguages,
            List<String> existingLanguages,
            List<String> missingLanguages) {

        this.metadata = metadata;
        this.metadataJsonExists = metadataJsonExists;
        this.supportedLanguages = supportedLanguages;
        this.existingLanguages = existingLanguages;
        this.missingLanguages = missingLanguages;
    }



    public TemplateMetadata getMetadata() {
        return metadata;
    }



    public boolean isMetadataJsonExists() {
        return metadataJsonExists;
    }



    public List<String> getSupportedLanguages() {
        return supportedLanguages;
    }



    public List<String> getExistingLanguages() {
        return existingLanguages;
    }



    public List<String> getMissingLanguages() {
        return missingLanguages;
    }



    @Override
    public String toString() {

        return "TemplateTranslationContext{" +
                "metadata=" + metadata +
                ", metadataJsonExists=" + metadataJsonExists +
                ", supportedLanguages=" + supportedLanguages +
                ", existingLanguages=" + existingLanguages +
                ", missingLanguages=" + missingLanguages +
                '}';
    }
}