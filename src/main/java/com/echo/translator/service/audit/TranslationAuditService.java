package com.echo.translator.service.audit;


import org.springframework.stereotype.Service;

import com.echo.translator.model.audit.TranslationAuditEntry;



@Service
public class TranslationAuditService {


    private final AuditFileWriter writer;



    public TranslationAuditService(
            AuditFileWriter writer) {

        this.writer = writer;
    }



    public void record(
            TranslationAuditEntry entry) {


        try {

            writer.write(entry);


        } catch(Exception e) {


            throw new RuntimeException(
                "Unable to save audit history",
                e
            );
        }
    }
}
