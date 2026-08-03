package com.echo.translator;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.translation.TranslationResult;
import com.echo.translator.service.translation.TemplateProcessingService;
import com.echo.translator.service.translation.TranslationWorkflowService;



@SpringBootApplication
public class TemplateTranslatorApplication
        implements CommandLineRunner {


    private static final Logger log =
            LoggerFactory.getLogger(
                    TemplateTranslatorApplication.class
            );


    private final TemplateProcessingService templateProcessingService;


    private final TranslationWorkflowService translationWorkflowService;



    public TemplateTranslatorApplication(
            TemplateProcessingService templateProcessingService,
            TranslationWorkflowService translationWorkflowService) {


        this.templateProcessingService =
                templateProcessingService;


        this.translationWorkflowService =
                translationWorkflowService;
    }



    public static void main(String[] args) {


        SpringApplication.run(
                TemplateTranslatorApplication.class,
                args
        );
    }




    @Override
    public void run(String... args)
            throws Exception {


        log.info("==================================");
        log.info("ED Template Translator Started");
        log.info("==================================");



        List<TemplateTranslationContext> contexts =
                templateProcessingService.process();



        for (TemplateTranslationContext context : contexts) {


            log.info(
                "Processing template: {}",
                context.getMetadata()
                       .getTemplateName()
            );


            List<TranslationResult> results =
                    translationWorkflowService.execute(
                            context
                    );


            log.info(
                "Translation completed. Files generated: {}",
                results.size()
            );
        }



        log.info("==================================");
        log.info("Template translation completed.");
        log.info("==================================");
    }
}