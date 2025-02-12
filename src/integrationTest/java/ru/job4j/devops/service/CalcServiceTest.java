package ru.job4j.devops.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;
import ru.job4j.devops.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CalcServiceTest extends ContainersConfig {

    @Autowired
    private CalcEventRepository calcEventRepository;

    @Autowired
    private CalcService calcService;

    @Autowired
    private UserRepository userRepository;

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
