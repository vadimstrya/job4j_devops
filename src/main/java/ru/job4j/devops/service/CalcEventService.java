package ru.job4j.devops.service;

import ru.job4j.devops.models.CalcEvent;

public interface CalcEventService {

    CalcEvent add(Long userId, Double first, Double second);
}
