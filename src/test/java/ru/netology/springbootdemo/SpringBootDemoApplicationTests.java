package ru.netology.springbootdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private final static GenericContainer<?> devContainer = new GenericContainer<>("devapp")
                                   .withExposedPorts(8080);
    private final static GenericContainer<?> prodContainer = new GenericContainer<>("prodapp")
                                     .withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
        devContainer.start();
        prodContainer.start();
    }

    @Test
    void devTest() {                                                        // localhost:8080/profile
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devContainer.getMappedPort(8080) + "/profile", String.class);

        Assertions.assertEquals("Current profile is prod", forEntity.getBody());
    }

    @Test           // todo
    void prodTest() {                                                        // localhost:8080/profile
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devContainer.getMappedPort(8080) + "/profile", String.class);

        Assertions.assertEquals("Current profile is prod", forEntity.getBody());
    }
}