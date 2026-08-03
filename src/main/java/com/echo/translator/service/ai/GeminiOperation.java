package com.echo.translator.service.ai;


@FunctionalInterface
public interface GeminiOperation<T> {


    T execute();

}