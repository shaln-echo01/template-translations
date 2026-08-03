package com.echo.translator.service.git;


import org.springframework.stereotype.Service;

import com.echo.translator.model.report.TranslationReport;



@Service
public class GitCommitServiceImpl
        implements GitCommitService {



    private final GitService gitService;



    public GitCommitServiceImpl(
            GitService gitService) {

        this.gitService = gitService;
    }



    @Override
    public void commitTranslation(
            TranslationReport report) {


        var changedFiles =
                gitService.getChangedFiles();



        if(changedFiles.isEmpty()) {

            return;
        }



        gitService.addFiles(
                changedFiles
        );



        String commitMessage =
                buildCommitMessage(
                        report
                );



        gitService.commit(
                commitMessage
        );
    }



    private String buildCommitMessage(
            TranslationReport report) {


        return "feat: add translated templates - "
                + report.getCreatedAt();

    }
}