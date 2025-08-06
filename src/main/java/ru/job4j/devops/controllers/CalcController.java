package ru.job4j.devops.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.devops.enums.CalcOperations;
import ru.job4j.devops.models.Result;
import ru.job4j.devops.models.TwoArgs;
import ru.job4j.devops.service.ResultService;

import java.time.LocalDate;
import java.util.List;

/** Контроллер для вычислений */
@RestController
@RequestMapping("/calc")
@RequiredArgsConstructor
public class CalcController {

    private final ResultService resultService;

    /**
     * Вычисление суммы
     *
     * @param twoArgs дто с аргументами
     * @return дто с результатом
     */
    @PostMapping("summarise")
    public ResponseEntity<Result> summarise(@RequestBody TwoArgs twoArgs) {
        var result = new Result();
        result.setFirstArg(twoArgs.getFirst());
        result.setSecondArg(twoArgs.getSecond());
        result.setResult(twoArgs.getFirst() + twoArgs.getSecond());
        result.setOperation(CalcOperations.ADDITION);
        result.setCreateDate(LocalDate.now());
        resultService.save(result);
        return ResponseEntity.ok(result);
    }

    /** Получение всех записей */
    @GetMapping("/")
    public ResponseEntity<List<Result>> logs() {
        return ResponseEntity.ok(resultService.findAll());
    }
}
