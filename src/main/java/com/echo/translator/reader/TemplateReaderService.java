package com.echo.translator.reader;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;


@Service
public class TemplateReaderService 
        implements TemplateReader {


    @Override
    public String read(Path path) throws IOException {


        return Files.readString(path);
    }
}