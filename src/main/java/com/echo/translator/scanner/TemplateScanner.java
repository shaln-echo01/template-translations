package com.echo.translator.scanner;

import java.io.IOException;
import java.util.List;

import com.echo.translator.model.metadata.TemplateMetadata;

public interface TemplateScanner {

    List<TemplateMetadata> scan() throws IOException;

}