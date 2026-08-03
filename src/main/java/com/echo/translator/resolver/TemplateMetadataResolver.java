package com.echo.translator.resolver;

import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.metadata.TemplateMetadata;

public interface TemplateMetadataResolver {

    TemplateTranslationContext resolve(TemplateMetadata metadata);
    

}