package com.echo.translator.service.report;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.echo.translator.model.report.TemplateTranslationReport;
import com.echo.translator.model.report.TranslationFileChange;
import com.echo.translator.model.report.TranslationReport;
import com.echo.translator.model.translation.TranslationResult;



@Service
public class TranslationReportServiceImpl
        implements TranslationReportService {



    @Override
    public TranslationReport generate(
            List<TranslationResult> results) {



        Map<String, List<TranslationResult>> grouped =
                results.stream()
                        .collect(
                            Collectors.groupingBy(
                                result ->
                                    result.getTask()
                                           .getTemplateName()
                            )
                        );



        List<TemplateTranslationReport> templates =
                grouped.entrySet()
                        .stream()
                        .map(entry -> {


                            String templateName =
                                    entry.getKey();



                            List<String> languages =
                                    entry.getValue()
                                            .stream()
                                            .map(result ->
                                                result.getTask()
                                                      .getTargetLanguage()
                                            )
                                            .toList();



                            List<TranslationFileChange> files =
                                    entry.getValue()
                                            .stream()
                                            .map(result ->
                                                new TranslationFileChange(
                                                    createFileName(result),
                                                    "CREATED"
                                                )
                                            )
                                            .toList();



                            return new TemplateTranslationReport(
                                    templateName,
                                    languages,
                                    files
                            );

                        })
                        .toList();



        return new TranslationReport(
                LocalDateTime.now(),
                templates
        );
    }



    private String createFileName(
            TranslationResult result) {


        return result.getTask()
                .getTemplateName()
                + "_"
                + result.getTask()
                        .getTargetLanguage()
                + ".mjml";
    }
}