package com.echo.translator.translation;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class LanguageGapAnalyzerService {


    public List<String> findMissingLanguages(
            List<String> supportedLanguages,
            List<String> existingLanguages) {


        return supportedLanguages.stream()
                .filter(language ->
                        !existingLanguages.contains(language))
                .toList();
    }
}