package ru.job4j.devops.service;

import ru.job4j.devops.models.Result;

import java.util.List;

public interface ResultService {

    void save(Result result);

    List<Result> findAll();
}
