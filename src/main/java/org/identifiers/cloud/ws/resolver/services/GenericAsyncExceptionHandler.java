package org.identifiers.cloud.ws.resolver.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class GenericAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {

        log.error("Exception message {}", throwable.getMessage());
        log.error("Method name {}", method.getName());
        for (Object param : obj) {
            log.error("Parameter {}, value = {}", param.getClass(), param);
        }
    }
}
