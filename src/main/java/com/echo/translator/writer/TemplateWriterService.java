package com.echo.translator.writer;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.echo.translator.model.translation.TranslationResult;
import com.echo.translator.model.translation.TranslationTask;



@Service
public class TemplateWriterService 
        implements TemplateWriter {


    @Override
    public void write(
            TranslationResult result)
            throws IOException {


        TranslationTask task =
                result.getTask();



        Path sourcePath =
                task.getSourceTemplatePath();



        String fileName =
                sourcePath
                .getFileName()
                .toString();



        String translatedFileName =
                fileName.replace(
                        "_en.mjml",
                        "_" 
                        + task.getTargetLanguage()
                        + ".mjml"
                );



        Path targetPath =
                sourcePath
                .getParent()
                .resolve(translatedFileName);



        Files.writeString(
                targetPath,
                result.getTranslatedContent(),
                StandardCharsets.UTF_8
        );
    }
}