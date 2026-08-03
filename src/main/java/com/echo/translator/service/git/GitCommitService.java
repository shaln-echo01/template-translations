package com.echo.translator.service.git;


import com.echo.translator.model.report.TranslationReport;


public interface GitCommitService {


    void commitTranslation(
            TranslationReport report
    );

}