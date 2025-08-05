package ru.job4j.devops.listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import ru.job4j.devops.config.ContainersConfig;
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
class CalcEventListenerTest extends ContainersConfig {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private CalcEventRepository calcEventRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void whenCreateCalcEvent() {
        var user = new User();
        user.setName("job4j");
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
