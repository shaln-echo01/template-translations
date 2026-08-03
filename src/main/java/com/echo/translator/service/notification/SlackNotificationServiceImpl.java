package com.echo.translator.service.notification;


import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.echo.translator.TemplateTranslatorApplication;
import com.echo.translator.config.SlackProperties;
import com.echo.translator.model.notification.SlackMessage;
import com.echo.translator.model.report.TemplateTranslationReport;
import com.echo.translator.model.report.TranslationFileChange;
import com.echo.translator.model.report.TranslationReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SlackNotificationServiceImpl
        implements SlackNotificationService {


    private final RestTemplate restTemplate;

    private final SlackProperties slackProperties;

    private static final Logger log =
            LoggerFactory.getLogger(TemplateTranslatorApplication.class);

    public SlackNotificationServiceImpl(
            RestTemplate restTemplate,
            SlackProperties slackProperties) {

        this.restTemplate = restTemplate;
        this.slackProperties = slackProperties;
    }



    @Override
    public void send(
            TranslationReport report) {


        log.info(
            "Sending translation report to Slack"
        );


        String message =
                buildMessage(report);


        SlackMessage slackMessage =
                new SlackMessage(message);


        restTemplate.postForEntity(
                slackProperties.getWebhookUrl(),
                slackMessage,
                String.class
        );


        log.info(
            "Slack notification sent successfully"
        );
    }



    private String buildMessage(
            TranslationReport report) {


        StringBuilder builder =
                new StringBuilder();


        builder.append(
                "*🚀 ED Template Translation Completed*\n\n"
        );


        builder.append(
                "*Completed At:* "
        );


        builder.append(
                report.getCreatedAt()
                        .format(
                            DateTimeFormatter
                            .ofPattern(
                                "yyyy-MM-dd HH:mm"
                            )
                        )
        );


        builder.append("\n\n");


        for(
            TemplateTranslationReport template :
            report.getTemplates()
        ) {


            builder.append(
                    "*Template:* "
            )
            .append(
                    template.getTemplateName()
            )
            .append("\n");


            builder.append(
                    "*Languages:* "
            )
            .append(
                    String.join(
                            ", ",
                            template.getTranslatedLanguages()
                    )
            )
            .append("\n");


            builder.append(
                    "*Files:*\n"
            );


            for(
                TranslationFileChange file :
                template.getFileChanges()
            ) {

                builder.append("• ")
                       .append(file)
                       .append("\n");
            }


            builder.append("\n");
        }


        return builder.toString();
    }
}