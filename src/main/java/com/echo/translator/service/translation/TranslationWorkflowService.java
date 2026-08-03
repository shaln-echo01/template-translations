package com.echo.translator.service.translation;


import java.util.List;

import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.translation.TranslationResult;


public interface TranslationWorkflowService {


    List<TranslationResult> execute(
            TemplateTranslationContext context
    );

}