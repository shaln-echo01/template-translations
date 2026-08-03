package com.echo.translator.config;


import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "translator")
public class TranslatorProperties {


    private String templatePath;


    public String getTemplatePath() {
        return templatePath;
    }


    public void setTemplatePath(
            String templatePath) {

        this.templatePath = templatePath;
    }



    public Path getTemplateDirectory() {

        return Path.of(
                templatePath
        );
    }
}