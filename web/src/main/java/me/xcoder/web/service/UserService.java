package me.xcoder.web.service;

import me.xcoder.web.domain.User;
import me.xcoder.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * find User by Id
     * @param id
     * @return
     */
    public Mono<User> findById(Long id) {
        return Mono.justOrEmpty(userRepository.findById(id));
    }

    /**
     * find List<User>
     * @return
     */
    public Flux<User> findAll() {
        return Flux.fromIterable(userRepository.findAll());
    }

    /**
     * save User
     * @param user
     * @return
     */
    @Transactional
    public Mono<User> add(Mono<User> user) {
        return Mono.justOrEmpty(userRepository.save(user.block()));
    }

    /**
     * update User
     * @return
     */
    @Transactional
    public Mono<User> update(Long id,Mono<User> user) {
        return Mono.justOrEmpty(userRepository.saveAndFlush(user.block()));
    }

}
