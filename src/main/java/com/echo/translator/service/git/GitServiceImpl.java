package com.echo.translator.service.git;


import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class GitServiceImpl
        implements GitService {



    @Override
    public List<String> getChangedFiles() {


        try {

            Process process =
                    new ProcessBuilder(
                            "git",
                            "status",
                            "--short"
                    )
                    .start();


            return new String(
                    process.getInputStream()
                            .readAllBytes()
            )
            .lines()
            .toList();


        } catch(IOException e) {

            throw new IllegalStateException(
                    "Git status failed",
                    e
            );
        }
    }



    @Override
    public void addFiles(
            List<String> files) {


        try {

            ProcessBuilder builder =
                    new ProcessBuilder();


            builder.command(
                    "git",
                    "add",
                    "."
            );


            builder.start()
                   .waitFor();


        } catch(Exception e) {


            throw new IllegalStateException(
                    "Git add failed",
                    e
            );
        }
    }



    @Override
    public void commit(
            String message) {


        try {


            Process process =
                    new ProcessBuilder(
                            "git",
                            "commit",
                            "-m",
                            message
                    )
                    .start();



            process.waitFor();



        } catch(Exception e) {


            throw new IllegalStateException(
                    "Git commit failed",
                    e
            );
        }
    }
}