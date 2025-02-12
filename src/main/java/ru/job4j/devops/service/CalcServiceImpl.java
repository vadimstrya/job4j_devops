package ru.job4j.devops.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.devops.CalcEventTypes;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;

@Service
@RequiredArgsConstructor
public class CalcServiceImpl implements CalcService {

    private final CalcEventRepository calcEventRepository;

    @Override
    public Long add(User user, int first, int second) {
        CalcEvent event = new CalcEvent();
        event.setUserId(user.getId());
        event.setFirst((double) first);
        event.setSecond((double) second);
        event.setType(CalcEventTypes.ADDITION);
        event.setResult((double) (first + second));
        return calcEventRepository.save(event).getId();
    }
}
