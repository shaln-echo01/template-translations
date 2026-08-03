package com.echo.translator.translation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface TranslationFileScanner {

    List<String> scan(Path templateDirectory)
            throws IOException;
}