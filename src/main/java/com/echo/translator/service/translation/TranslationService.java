package com.echo.translator.service.translation;


import com.echo.translator.model.ai.GeminiTranslationResponse;
import com.echo.translator.model.translation.TranslationTask;


public interface TranslationService {


    GeminiTranslationResponse translate(
            TranslationTask task
    );

}