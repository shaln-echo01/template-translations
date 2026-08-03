package com.echo.translator.service.translation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.metadata.TemplateMetadata;
import com.echo.translator.scanner.TemplateScanner;
import com.echo.translator.resolver.TemplateMetadataResolver;

@Component
public class TranslationRunner implements CommandLineRunner {

    private final TemplateScanner templateScanner;
    private final TemplateMetadataResolver templateMetadataResolver;
    public TranslationRunner(
            TemplateScanner templateScanner,
            TemplateMetadataResolver templateMetadataResolver) {

        this.templateScanner = templateScanner;
        this.templateMetadataResolver = templateMetadataResolver;
    }

    @Override
    public void run(String... args) throws IOException {

        System.out.println("==================================");
        System.out.println("ED Template Translator Started");
        System.out.println("==================================");

//        List<Path> templates = templateScanner.scan();
        List<TemplateMetadata> templates = templateScanner.scan();

        templates.forEach(template -> {

            TemplateTranslationContext context =
                    templateMetadataResolver.resolve(template);


            System.out.println(context);

        });

//        templates.forEach(System.out::println);

        System.out.println("Template scan completed.");
    }
}