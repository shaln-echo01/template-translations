package com.echo.translator.service.report;


import java.util.List;

import com.echo.translator.model.report.TranslationReport;
import com.echo.translator.model.translation.TranslationResult;



public interface TranslationReportService {


    TranslationReport generate(
            List<TranslationResult> results
    );

}