package com.echo.translator.model.metadata;

import java.nio.file.Path;

public class TemplateMetadata {

    private final String templateName;

    private final Path englishTemplatePath;

    private final Path metadataJsonPath;


    public TemplateMetadata(
            String templateName,
            Path englishTemplatePath,
            Path metadataJsonPath) {

        this.templateName = templateName;
        this.englishTemplatePath = englishTemplatePath;
        this.metadataJsonPath = metadataJsonPath;
    }


    public String getTemplateName() {
        return templateName;
    }


    public Path getEnglishTemplatePath() {
        return englishTemplatePath;
    }


    public Path getMetadataJsonPath() {
        return metadataJsonPath;
    }
    @Override
    public String toString() {
        return "TemplateMetadata{" +
                "templateName='" + templateName + '\'' +
                ", englishTemplatePath=" + englishTemplatePath +
                ", metadataJsonPath=" + metadataJsonPath +
                '}';
    }
}