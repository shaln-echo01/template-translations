package com.echo.translator.service.ai;


import com.echo.translator.model.ai.GeminiTranslationRequest;
import com.echo.translator.model.ai.GeminiTranslationResponse;



public interface GeminiService {


    GeminiTranslationResponse translate(
            GeminiTranslationRequest request
    );

}