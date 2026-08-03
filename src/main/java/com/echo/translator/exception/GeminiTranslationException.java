package com.echo.translator.exception;


public class GeminiTranslationException
        extends RuntimeException {

// added this just because it is serializable class and Eclipse was giving  warning  
    private static final long serialVersionUID = 1L; 

	public GeminiTranslationException(
            String message,
            Throwable cause) {

        super(message, cause);
    }


    public GeminiTranslationException(
            String message) {

        super(message);
    }
}