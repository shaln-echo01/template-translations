package com.echo.translator.service.audit;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.echo.translator.model.audit.TranslationAuditEntry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class AuditFileWriter {


    private final ObjectMapper objectMapper;



    private final Path auditFile =
            Path.of(
              "translator-data/audit/translation-history.json"
            );



    public AuditFileWriter(
            ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }



    public void write(
            TranslationAuditEntry entry)
            throws IOException {


        List<TranslationAuditEntry> history =
                read();



        history.add(entry);



        Files.createDirectories(
                auditFile.getParent()
        );



        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(
                    auditFile.toFile(),
                    history
                );
    }



    private List<TranslationAuditEntry> read()
            throws IOException {


        if(!Files.exists(auditFile)) {

            return new ArrayList<>();
        }



        return objectMapper.readValue(
                auditFile.toFile(),
                new TypeReference<
                List<TranslationAuditEntry>>() {}
        );
    }
}