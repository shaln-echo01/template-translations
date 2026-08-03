package com.echo.translator.writer;


import java.io.IOException;

import com.echo.translator.model.translation.TranslationResult;


public interface TemplateWriter {


    void write(
            TranslationResult result
    ) throws IOException;

}