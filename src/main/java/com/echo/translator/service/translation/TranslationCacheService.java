package com.echo.translator.service.translation;


import org.springframework.stereotype.Service;

import com.echo.translator.model.context.TemplateTranslationContext;



@Service
public class TranslationCacheService {


    public boolean alreadyTranslated(
            TemplateTranslationContext context,
            String language) {


        return context.getExistingLanguages()
                .contains(language);
    }
}