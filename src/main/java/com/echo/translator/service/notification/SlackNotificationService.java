package com.echo.translator.service.notification;


import com.echo.translator.model.report.TranslationReport;


public interface SlackNotificationService {


    void send(
            TranslationReport report
    );

}