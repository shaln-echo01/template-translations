package com.echo.translator.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        TranslatorProperties.class,
        GeminiProperties.class
})
public class AppConfig {

}