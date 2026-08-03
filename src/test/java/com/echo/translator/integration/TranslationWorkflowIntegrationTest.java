package com.echo.translator.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.metadata.TemplateMetadata;
import com.echo.translator.model.report.TranslationReport;
import com.echo.translator.model.translation.TranslationResult;
import com.echo.translator.model.translation.TranslationTask;
import com.echo.translator.service.notification.SlackNotificationService;
import com.echo.translator.service.report.TranslationReportService;
import com.echo.translator.service.translation.MetadataUpdater;
import com.echo.translator.service.translation.TranslationPipelineService;
import com.echo.translator.service.translation.TranslationTaskBuilderService;
import com.echo.translator.service.translation.TranslationWorkflowServiceImpl;
import com.echo.translator.writer.TemplateWriter;


@ExtendWith(MockitoExtension.class)
class TranslationWorkflowIntegrationTest {


    @Mock
    private TranslationTaskBuilderService taskBuilderService;


    @Mock
    private TranslationPipelineService pipelineService;


    @Mock
    private TemplateWriter templateWriter;


    @Mock
    private MetadataUpdater metadataUpdater;


    @Mock
    private TranslationReportService translationReportService;


    @Mock
    private SlackNotificationService slackNotificationService;



    @InjectMocks
    private TranslationWorkflowServiceImpl workflowService;



    @Test
    void shouldExecuteTranslationWorkflow()
            throws Exception {


        TemplateMetadata metadata =
                new TemplateMetadata(
                        "CasePresentationApproved",
                        Path.of(
                            "CasePresentationApproved_en.mjml"
                        ),
                        Path.of(
                            "CasePresentationApproved.json"
                        )
                );


        TemplateTranslationContext context =
                new TemplateTranslationContext(
                        metadata,
                        true,
                        List.of(
                                "en",
                                "vi"
                        ),
                        List.of(
                                "en"
                        ),
                        List.of(
                                "vi"
                        )
                );


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
                mock(
                    TranslationReport.class
                );


        when(
            taskBuilderService.build(context)
        )
        .thenReturn(
                List.of(task)
        );


        when(
            pipelineService.execute(
                    List.of(task)
            )
        )
        .thenReturn(
                List.of(result)
        );


        when(
            translationReportService.generate(
                    List.of(result)
            )
        )
        .thenReturn(
                report
        );



        List<TranslationResult> results =
                workflowService.execute(
                        context
                );



        assertEquals(
                1,
                results.size()
        );


        verify(
                templateWriter
        )
        .write(result);



        verify(
                metadataUpdater
        )
        .update(result);



        verify(
                slackNotificationService
        )
        .send(report);
    }
}