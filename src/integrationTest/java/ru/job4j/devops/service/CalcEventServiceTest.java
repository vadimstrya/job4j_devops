package ru.job4j.devops.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CalcEventServiceTest extends ContainersConfig {

    @Autowired
    private CalcEventService calcEventService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenAddEvent() {
        User user = new User();
        user.setName("Job4j");
        userRepository.save(user);

        Double first = 1D;
        Double second = 2D;
        Double result = first + second;
        CalcEvent event = calcEventService.add(user.getId(), first, second);

        assertThat(event.getId()).isNotNull();
        assertThat(event.getResult()).isEqualTo(result);
    }
}
