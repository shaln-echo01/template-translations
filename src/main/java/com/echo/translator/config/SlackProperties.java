package com.echo.translator.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "slack")
public class SlackProperties {


    private String webhookUrl;


    public String getWebhookUrl() {
        return webhookUrl;
    }


    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }
}