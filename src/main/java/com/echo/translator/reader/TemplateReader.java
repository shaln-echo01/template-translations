package com.echo.translator.reader;


import java.io.IOException;
import java.nio.file.Path;


public interface TemplateReader {


    String read(Path templatePath)
            throws IOException;

}