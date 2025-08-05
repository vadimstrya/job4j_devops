package ru.job4j.devops.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.repository.CalcEventRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class CalcEventListener {

    private final CalcEventRepository calcEventRepository;

    @KafkaListener(topics = "createCalcEvent", groupId = "job4j")
    public void createCalcEvent(CalcEvent event) {
        log.debug("Received CalcEvent from user: {}", event.getUserId());
        calcEventRepository.save(event);
    }
}
