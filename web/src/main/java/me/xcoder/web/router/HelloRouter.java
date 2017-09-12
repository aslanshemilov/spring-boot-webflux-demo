package me.xcoder.web.router;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class HelloRouter {

    public RouterFunction<ServerResponse> routes() {
        return route(GET("/"), this::index);
    }

    private Mono<ServerResponse> index(ServerRequest req) {
        return ok().body(Flux.just("Webflux works!"), String.class);
    }
}
