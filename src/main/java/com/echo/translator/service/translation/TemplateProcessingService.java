package com.echo.translator.service.translation;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.metadata.TemplateMetadata;
import com.echo.translator.resolver.TemplateMetadataResolver;
import com.echo.translator.scanner.TemplateScanner;


@Service
public class TemplateProcessingService {


    private final TemplateScanner templateScanner;

    private final TemplateMetadataResolver templateMetadataResolver;


    public TemplateProcessingService(
            TemplateScanner templateScanner,
            TemplateMetadataResolver templateMetadataResolver) {

        this.templateScanner = templateScanner;
        this.templateMetadataResolver = templateMetadataResolver;
    }


    public List<TemplateTranslationContext> process()
            throws IOException {


        List<TemplateMetadata> templates =
                templateScanner.scan();


        return templates.stream()
                .map(templateMetadataResolver::resolve)
                .toList();
    }
}