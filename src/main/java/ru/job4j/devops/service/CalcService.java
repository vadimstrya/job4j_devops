package ru.job4j.devops.service;

import ru.job4j.devops.models.User;

public interface CalcService {

    Long add(User user, int first, int second);
}
