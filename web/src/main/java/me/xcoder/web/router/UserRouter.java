package me.xcoder.web.router;

import me.xcoder.web.domain.User;
import me.xcoder.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserRouter {

    @Autowired
    private UserService userService;

    public RouterFunction<ServerResponse> routes() {
        return nest(path("/user"),
                route(GET("/list").and(accept(APPLICATION_JSON)), this::listAll)
                        .andRoute(GET("/list2").and(accept(APPLICATION_JSON)), this::findAllMono)
                        .andRoute(POST("/save").and(accept(APPLICATION_JSON)), this::save)
                        .andRoute(POST("/update/{id}").and(accept(APPLICATION_JSON)), this::update));
    }

    private Mono<ServerResponse> listAll(ServerRequest req) {
        return ok().body(userService.findAll(req), User.class);
    }

    private Mono<ServerResponse> findAllMono(ServerRequest req) {
        return ok().body(BodyInserters.fromPublisher(userService.findAllMono(),
                new ParameterizedTypeReference<List<User>>() {
                }));
    }

    private Mono<ServerResponse> save(ServerRequest req) {
        return ok().body(Flux.just(userService.save(req)), User.class);
    }

    private Mono<ServerResponse> update(ServerRequest req) {
        return ok().body(Flux.just(userService.update(req)), User.class);
    }

}
