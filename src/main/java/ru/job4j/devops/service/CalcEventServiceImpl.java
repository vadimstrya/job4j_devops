package ru.job4j.devops.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.devops.enums.CalcEventTypes;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.repository.CalcEventRepository;

@Service
@RequiredArgsConstructor
public class CalcEventServiceImpl implements CalcEventService {

    private final CalcEventRepository calcEventRepository;

    @Override
    public CalcEvent add(Long userId, Double first, Double second) {
        return createEvent(userId, CalcEventTypes.ADDITION, first, second);
    }

    private CalcEvent createEvent(Long userId, CalcEventTypes type, Double first, Double second) {
        Double result = switch (type) {
            case ADDITION -> first + second;
            case SUBTRACTION -> first - second;
            case MULTIPLICATION -> first * second;
            case DIVISION -> first / second;
        };

        CalcEvent event = new CalcEvent();
        event.setUserId(userId);
        event.setType(CalcEventTypes.ADDITION);
        event.setFirstArg(first);
        event.setSecondArg(second);
        event.setResult(result);
        return calcEventRepository.save(event);
    }
}
