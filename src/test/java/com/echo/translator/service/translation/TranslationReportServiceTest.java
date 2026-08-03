package com.echo.translator.service.translation;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.echo.translator.model.report.TemplateTranslationReport;
import com.echo.translator.model.report.TranslationReport;
import com.echo.translator.model.translation.TranslationResult;
import com.echo.translator.model.translation.TranslationTask;
import com.echo.translator.service.report.TranslationReportService;
import com.echo.translator.service.report.TranslationReportServiceImpl;


class TranslationReportServiceTest {


    private final TranslationReportService service =
            new TranslationReportServiceImpl();



    @Test
    void shouldGenerateTranslationReport() {


        TranslationTask task =
                new TranslationTask(
                        "CasePresentationApproved",
                        Path.of(
                            "CasePresentationApproved_en.mjml"
                        ),
                        Path.of(
                            "CasePresentationApproved.json"
                        ),
                        "vi"
                );


        TranslationResult result =
                new TranslationResult(
                        task,
                        "Translated MJML"
                );



        TranslationReport report =
                service.generate(
                        List.of(result)
                );



        assertNotNull(
                report.getCreatedAt()
        );


        assertEquals(
                1,
                report.getTemplates()
                      .size()
        );


        TemplateTranslationReport templateReport =
                report.getTemplates()
                      .get(0);



        assertEquals(
                "CasePresentationApproved",
                templateReport.getTemplateName()
        );



        assertEquals(
                List.of("vi"),
                templateReport.getTranslatedLanguages()
        );



        assertEquals(
                1,
                templateReport.getFileChanges()
                      .size()
        );


        assertEquals(
                "CasePresentationApproved_vi.mjml",
                templateReport.getFileChanges()
                              .get(0)
                              .getFileName()
        );


        assertEquals(
                "CREATED",
                templateReport.getFileChanges()
                              .get(0)
                              .getAction()
        );
    }



    @Test
    void shouldGroupMultipleLanguagesForSameTemplate() {


        TranslationTask viTask =
                new TranslationTask(
                        "CasePresentationApproved",
                        Path.of("template_en.mjml"),
                        Path.of("template.json"),
                        "vi"
                );


        TranslationTask arTask =
                new TranslationTask(
                        "CasePresentationApproved",
                        Path.of("template_en.mjml"),
                        Path.of("template.json"),
                        "ar"
                );



        TranslationResult viResult =
                new TranslationResult(
                        viTask,
                        "Vietnamese Content"
                );


        TranslationResult arResult =
                new TranslationResult(
                        arTask,
                        "Arabic Content"
                );



        TranslationReport report =
                service.generate(
                        List.of(
                                viResult,
                                arResult
                        )
                );



        TemplateTranslationReport templateReport =
                report.getTemplates()
                      .get(0);



        assertEquals(
                2,
                templateReport.getTranslatedLanguages()
                              .size()
        );


        assertEquals(
                2,
                templateReport.getFileChanges()
                              .size()
        );
    }
}