package ru.job4j.devops.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.repository.CalcEventRepository;

@Slf4j
@Component
@AllArgsConstructor
public class CalcEventListener {

    private final CalcEventRepository calcEventRepository;

    @KafkaListener(topics = "signupEvent", groupId = "job4j")
    public void signup(CalcEvent event) {
        log.debug("Received event: {}, {}", event.getId(), event.getType());
        calcEventRepository.save(event);
    }
}
