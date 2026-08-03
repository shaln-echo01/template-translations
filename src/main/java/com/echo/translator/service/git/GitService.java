package com.echo.translator.service.git;


import java.util.List;


public interface GitService {


    List<String> getChangedFiles();


    void addFiles(
            List<String> files
    );


    void commit(
            String message
    );

}