package me.xcoder.web.controller;

import me.xcoder.web.domain.User;
import me.xcoder.web.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private WebTestClient webClient;

//    @Before
//    public void setup() throws Exception{
//         webClient = WebTestClient
//                .bindToController(new UserController())
//                .configureClient().baseUrl("/person")
//                .build();
//    }

    @Test
    public void testCreate() {
        User user = new User();
        user.setName("abcd");
        this.webClient.post().uri("/person/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(user),User.class).exchange()
                .expectStatus().is4xxClientError();
//                .expectBody(User.class).isEqualTo(user);
//                .consumeWith(result -> assertEquals("Response result","abcd", result.getResponseBody().getName()));
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setName("findById");
        User newUser = userService.add(Mono.just(user)).block();
        this.webClient.get().uri(String.format("/person/%s",newUser.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class).isEqualTo(newUser);
    }

    @Test
    public void testList() {

    }

    @Test
    public void testUpdate(){

    }

}
