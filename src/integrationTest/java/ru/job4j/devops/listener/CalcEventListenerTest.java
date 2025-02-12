package ru.job4j.devops.listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import ru.job4j.devops.CalcEventTypes;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;
import ru.job4j.devops.repository.UserRepository;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
class CalcEventListenerTest extends ContainersConfig {

    @Autowired
    private CalcEventRepository calcEventRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    void whenSignupNewEvent() {
        var user = new User();
        user.setName("Job4j new member : " + System.nanoTime());
        userRepository.save(user);

        CalcEvent event = new CalcEvent();
        event.setUserId(user.getId());
        event.setFirst((double) 4);
        event.setSecond((double) 5);
        event.setType(CalcEventTypes.MULTIPLICATION);
        event.setResult((double) (20));

        kafkaTemplate.send("signupEvent", event);
        await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, SECONDS)
                .untilAsserted(() -> {
                    var optionalEvent = calcEventRepository.findLastByUserId(user.getId());
                    assertThat(optionalEvent).isPresent();
                    assertThat(optionalEvent.get().getResult()).isEqualTo(Double.valueOf(20));
                });
    }
}
