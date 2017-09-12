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

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;

import reactor.core.publisher.Mono;

@Component
public class UserRouter {

    @Autowired
    private UserService userService;

    public RouterFunction<ServerResponse> routes() {
        return nest(path("/user"),
                route(GET("/list").and(accept(APPLICATION_JSON)), this::listAll)
                        .andRoute(GET("/{id}").and(accept(APPLICATION_JSON)), this::findById)
                        .andRoute(POST("/add").and(accept(APPLICATION_JSON)), this::add)
                        .andRoute(POST("/update/{id}").and(accept(APPLICATION_JSON)), this::update));
    }

    private Mono<ServerResponse> findById(ServerRequest req){
        Long id = Long.valueOf(req.pathVariable("id"));
        return ok().body(userService.findById(id),User.class);
    }

    private Mono<ServerResponse> listAll(ServerRequest req) {
        return ok().body(userService.findAll(), User.class);
    }

    private Mono<ServerResponse> add(ServerRequest req) {
        User user = req.bodyToMono(User.class).block();
        return ok().body(userService.add(user), User.class);
    }

    private Mono<ServerResponse> update(ServerRequest req) {
        Long id = Long.valueOf(req.pathVariable("id"));
        User user = req.bodyToMono(User.class).block();
        return ok().body(userService.update(id,user), User.class);
    }

}
