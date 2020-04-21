package ru.demo.reactiverestservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        return RouterFunctions
                .route(
                        RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        greetingHandler::hello
                )
                .and(
                        getPostGreetingRoute(greetingHandler::hello)
                );
    }

    private RouterFunction<ServerResponse> getPostGreetingRoute(HandlerFunction<ServerResponse> handlerFunction) {
        return RouterFunctions
                .route(
                        RequestPredicates.POST("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        handlerFunction
                );
    }
}
