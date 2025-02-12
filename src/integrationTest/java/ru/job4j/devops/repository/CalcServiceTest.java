package ru.job4j.devops.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.job4j.devops.models.User;
import ru.job4j.devops.service.CalcService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CalcServiceTest {

    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16-alpine").withReuse(true);

    @Autowired
    private CalcEventRepository calcEventRepository;

    @Autowired
    private CalcService calcService;

    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    public static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        POSTGRES.start();
    }

    @AfterAll
    static void afterAll() {
        POSTGRES.stop();
    }

    @Test
    public void when5Add6ThenResult11() {
        var user = new User();
        user.setName("Job4j");
        userRepository.save(user);
        Long calEventId = calcService.add(user, 5, 6);
        var foundCalcEvent = calcEventRepository.findById(calEventId);
        assertThat(foundCalcEvent).isPresent();
        assertThat(foundCalcEvent.get().getUserId()).isEqualTo(user.getId());
        assertThat(foundCalcEvent.get().getResult()).isEqualTo(Double.valueOf(11));
    }
}
