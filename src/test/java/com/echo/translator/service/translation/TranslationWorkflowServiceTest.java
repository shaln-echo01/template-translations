package com.echo.translator.service.translation;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import com.echo.translator.model.translation.TranslationResult;
import com.echo.translator.model.translation.TranslationTask;
import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.writer.TemplateWriter;
import com.echo.translator.service.report.TranslationReportService;
import com.echo.translator.service.notification.SlackNotificationService;



@ExtendWith(MockitoExtension.class)
class TranslationWorkflowServiceTest {


    @Mock
    private TranslationTaskBuilderService taskBuilderService;


    @Mock
    private TranslationPipelineService pipelineService;


    @Mock
    private TemplateWriter templateWriter;


    @Mock
    private MetadataUpdater metadataUpdater;


    @Mock
    private TranslationReportService reportService;


    @Mock
    private SlackNotificationService slackService;



    @InjectMocks
    private TranslationWorkflowServiceImpl service;



    @Test
    void shouldExecuteTranslationWorkflow()
            throws Exception {


        TemplateTranslationContext context =
                mock(
                  TemplateTranslationContext.class
                );


        TranslationTask task =
                mock(
                  TranslationTask.class
                );


        TranslationResult result =
                mock(
                  TranslationResult.class
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



        List<TranslationResult> response =
                service.execute(context);



        assertEquals(
                1,
                response.size()
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
            slackService
        )
        .send(any());
    }
}