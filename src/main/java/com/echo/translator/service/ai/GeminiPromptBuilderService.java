package com.echo.translator.service.ai;


import org.springframework.stereotype.Service;

import com.echo.translator.model.ai.GeminiPrompt;



@Service
public class GeminiPromptBuilderService {



    public GeminiPrompt build(
            String mjmlContent,
            String targetLanguage) {


        String prompt =
                """
                You are an expert MJML email translator.

                Translate only visible text content.

                Rules:
                1. Do not modify MJML tags.
                2. Do not modify HTML attributes.
                3. Do not change variables like {{value}}.
                4. Preserve indentation and formatting.
                5. Return only translated MJML.
                6. Do not add explanations.

                Target Language:
                %s

                MJML Content:

                %s
                """
                .formatted(
                    targetLanguage,
                    mjmlContent
                );


        return new GeminiPrompt(prompt);
    }
}