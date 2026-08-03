package com.echo.translator.validation;


import org.springframework.stereotype.Service;

import com.echo.translator.exception.GeminiTranslationException;



@Service
public class MjmlTranslationValidator
        implements TranslationValidator {



    @Override
    public void validate(
            String content) {


        if(content == null ||
           content.isBlank()) {


            throw new GeminiTranslationException(
                    "Translated content is empty"
            );
        }



        if(!content.contains("<mjml>")) {


            throw new GeminiTranslationException(
                    "Invalid MJML: missing <mjml> tag"
            );
        }



        if(!content.contains("</mjml>")) {


            throw new GeminiTranslationException(
                    "Invalid MJML: missing closing tag"
            );
        }
    }
}