package com.echo.translator.service.translation;


import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Service;

import com.echo.translator.model.ai.GeminiTranslationRequest;
import com.echo.translator.model.ai.GeminiTranslationResponse;
import com.echo.translator.model.translation.TranslationResult;
import com.echo.translator.model.translation.TranslationTask;
import com.echo.translator.service.ai.GeminiService;
import com.echo.translator.validation.TranslationValidator;



@Service
public class TranslationPipelineService {



    private final GeminiService geminiService;
    private final TranslationValidator validator;


    public TranslationPipelineService(
            GeminiService geminiService,
            TranslationValidator validator) {

        this.geminiService = geminiService;
        this.validator = validator;
    }



    public List<TranslationResult> execute(
            List<TranslationTask> tasks)
            throws IOException {



        return tasks.stream()
        		.map(task -> {

        		    try {

        		        return translate(task);

        		    } catch(Exception e) {

        		        throw new RuntimeException(
        		            "Failed translation: "
        		            + task.getTemplateName(),
        		            e
        		        );
        		    }
        		})
                .toList();
    }



    private TranslationResult translate(
            TranslationTask task) {


        try {


            String content =
                    Files.readString(
                        task.getSourceTemplatePath()
                    );



            GeminiTranslationRequest request =
                    new GeminiTranslationRequest(
                            content,
                            task.getTargetLanguage()
                    );



            GeminiTranslationResponse response =
                    geminiService.translate(
                            request
                    );



            String translatedContent =
                    response.getTranslatedContent();



            validator.validate(
                    translatedContent
            );



            return new TranslationResult(
                    task,
                    translatedContent
            );


        } catch (IOException e) {


            throw new RuntimeException(
                    "Translation failed for "
                    + task.getTemplateName(),
                    e
            );
        }
    }
}