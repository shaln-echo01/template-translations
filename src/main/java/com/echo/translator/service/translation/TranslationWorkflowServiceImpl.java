package com.echo.translator.service.translation;
import com.echo.translator.model.report.TranslationReport;
import com.echo.translator.service.report.TranslationReportService;
import com.echo.translator.service.audit.TranslationAuditService;
import com.echo.translator.service.notification.SlackNotificationService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.echo.translator.TemplateTranslatorApplication;
import com.echo.translator.model.audit.TranslationAuditEntry;
import com.echo.translator.model.context.TemplateTranslationContext;
import com.echo.translator.model.translation.TranslationResult;
import com.echo.translator.model.translation.TranslationTask;
import com.echo.translator.writer.TemplateWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TranslationWorkflowServiceImpl
        implements TranslationWorkflowService {
	private static final Logger log =
            LoggerFactory.getLogger(
                    TemplateTranslatorApplication.class
            );

    private final TranslationTaskBuilderService taskBuilderService;

    private final TranslationPipelineService pipelineService;

    private final TemplateWriter templateWriter;

    private final MetadataUpdater metadataUpdater;
    
    private final TranslationReportService translationReportService;

    private final SlackNotificationService slackNotificationService;

    private final TranslationAuditService auditService;

    public TranslationWorkflowServiceImpl(
            TranslationTaskBuilderService taskBuilderService,
            TranslationPipelineService pipelineService,
            TemplateWriter templateWriter,
            MetadataUpdater metadataUpdater,
            TranslationReportService translationReportService,
            SlackNotificationService slackNotificationService,
            TranslationAuditService auditService) {


        this.taskBuilderService = taskBuilderService;

        this.pipelineService = pipelineService;

        this.templateWriter = templateWriter;

        this.metadataUpdater = metadataUpdater;

        this.translationReportService = translationReportService;

        this.slackNotificationService = slackNotificationService;
        
        this.auditService = auditService;
    }



    @Override
    public List<TranslationResult> execute(
            TemplateTranslationContext context) {


        try {


            List<TranslationTask> tasks =
                    taskBuilderService.build(context);

//            List<TranslationResult> results =
//            		if(tasks.isEmpty()) {
//
//            		    log.info(
//            		        "No missing translations found"
//            		    );
//
//            		    return List.of();
//            		}


            		List<TranslationResult> results =
            		        pipelineService.execute(tasks);



            		results.forEach(result -> {

            		    try {

            		        templateWriter.write(result);

            		        metadataUpdater.update(result);


            		        auditService.record(
            		                new TranslationAuditEntry(
            		                    result.getTask()
            		                          .getTemplateName(),

            		                    result.getTask()
            		                          .getTargetLanguage(),

            		                    "SUCCESS",

            		                    LocalDateTime.now()
            		                )
            		        );


            		    } catch (IOException e) {


            		        auditService.record(
            		                new TranslationAuditEntry(
            		                    result.getTask()
            		                          .getTemplateName(),

            		                    result.getTask()
            		                          .getTargetLanguage(),

            		                    "FAILED",

            		                    LocalDateTime.now()
            		                )
            		        );


            		        throw new RuntimeException(
            		                "Unable to persist translation",
            		                e
            		        );
            		    }
            		});



            TranslationReport report =
                    translationReportService.generate(
                            results
                    );



            slackNotificationService.send(
                    report
            );



            return results;



        } catch (Exception e) {


            throw new IllegalStateException(
                    "Translation workflow failed for template: "
                    + context.getMetadata()
                             .getTemplateName(),
                    e
            );
        }
    }
}