package ru.job4j.devops.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.devops.enums.CalcOperations;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.repository.CalcEventRepository;

@Service
@RequiredArgsConstructor
public class CalcEventServiceImpl implements CalcEventService {

    private final CalcEventRepository calcEventRepository;

    @Override
    public CalcEvent add(Long userId, Double first, Double second) {
        return createEvent(userId, CalcOperations.ADDITION, first, second);
    }

    private CalcEvent createEvent(Long userId, CalcOperations type, Double first, Double second) {
        Double result = switch (type) {
            case ADDITION -> first + second;
            case SUBTRACTION -> first - second;
            case MULTIPLICATION -> first * second;
            case DIVISION -> first / second;
        };

        CalcEvent event = new CalcEvent();
        event.setUserId(userId);
        event.setType(CalcOperations.ADDITION);
        event.setFirstArg(first);
        event.setSecondArg(second);
        event.setResult(result);
        return calcEventRepository.save(event);
    }
}
