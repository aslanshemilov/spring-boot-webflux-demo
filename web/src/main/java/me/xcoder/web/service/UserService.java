package me.xcoder.web.service;

import java.util.List;
import java.util.Optional;

import me.xcoder.web.domain.User;
import me.xcoder.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<List<User>> findAllMono() {
        return Mono.just(userRepository.findAll());
    }

    public Flux<User> findAll(ServerRequest req) {
        return Flux.fromIterable(userRepository.findAll());
    }

    @Transactional
    public User save(ServerRequest req) {
        return userRepository.save(req.bodyToMono(User.class).block());
    }

    @Transactional
    public User update(ServerRequest req) {
        System.out.println(" id -- " + req.pathVariable("id"));
        User person = req.bodyToMono(User.class).block();
        person.setId(Long.valueOf(req.pathVariable("id")));
        return userRepository.save(person);
    }

}
