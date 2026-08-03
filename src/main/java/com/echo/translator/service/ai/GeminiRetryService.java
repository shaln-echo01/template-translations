package com.echo.translator.service.ai;


import org.springframework.stereotype.Service;


@Service
public class GeminiRetryService {



    private static final int MAX_RETRY = 3;



    public <T> T execute(
            GeminiOperation<T> operation) {


        int attempt = 0;


        while(attempt < MAX_RETRY) {

            try {

                return operation.execute();


            } catch(Exception e) {


                attempt++;


                if(attempt == MAX_RETRY) {

                    throw e;
                }


                try {

                    Thread.sleep(
                            2000
                    );

                } catch(
                    InterruptedException ex) {

                    Thread.currentThread()
                            .interrupt();
                }
            }
        }


        throw new IllegalStateException(
                "Retry failed"
        );
    }
}