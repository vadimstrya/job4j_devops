package ru.job4j.devops.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.devops.models.Result;
import ru.job4j.devops.models.TwoArgs;

/** Контроллер для вычислений */
@RestController
@RequestMapping("calc")
public class CalcController {

    /**
     * Вычисление суммы
     *
     * @param twoArgs дто с аргументами
     * @return дто с результатом
     */
    @PostMapping("summarise")
    public ResponseEntity<Result> summarise(@RequestBody TwoArgs twoArgs) {
        var result = twoArgs.getFirst() + twoArgs.getSecond();
        return ResponseEntity.ok(new Result(result));
    }

    /**
     * Вычисление произведения
     *
     * @param twoArgs дто с аргументами
     * @return дто с результатом
     */
    @PostMapping("times")
    public ResponseEntity<Result> times(@RequestBody TwoArgs twoArgs) {
        var result = twoArgs.getFirst() * twoArgs.getSecond();
        return ResponseEntity.ok(new Result(result));
    }
}
