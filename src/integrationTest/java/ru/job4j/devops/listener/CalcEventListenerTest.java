package ru.job4j.devops.listener;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;
import ru.job4j.devops.enums.CalcEventTypes;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;
import ru.job4j.devops.repository.UserRepository;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
class CalcEventListenerTest {

    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:17")
            .withReuse(true);

    private static final KafkaContainer KAFKA = new KafkaContainer(DockerImageName.parse("apache/kafka:3.7.2"))
            .withReuse(true);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private CalcEventRepository calcEventRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {
        POSTGRES.start();
        KAFKA.start();
    }

    @AfterAll
    static void afterAll() {
        POSTGRES.stop();
        KAFKA.stop();
    }

    @DynamicPropertySource
    public static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
        registry.add("spring.kafka.bootstrap-servers", KAFKA::getBootstrapServers);
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }

    @Test
    void whenCreateCalcEvent() {
        var user = new User();
        user.setName("Job4j");
        userRepository.save(user);

        var event = new CalcEvent();
        event.setUserId(user.getId());
        event.setType(CalcEventTypes.ADDITION);
        event.setFirstArg(1D);
        event.setSecondArg(2D);
        event.setResult(3D);

        kafkaTemplate.send("createCalcEvent", event);
        await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, SECONDS)
                .untilAsserted(() -> {
                    var optionalEvent = calcEventRepository.findLastByUserId(user.getId());
                    assertThat(optionalEvent).isPresent();
                });
    }
}
