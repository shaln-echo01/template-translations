package com.echo.translator.service.translation;


import java.io.IOException;

import com.echo.translator.model.translation.TranslationResult;


public interface MetadataUpdater {


    void update(
            TranslationResult result
    ) throws IOException;

}