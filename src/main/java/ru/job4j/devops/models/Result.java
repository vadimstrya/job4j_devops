package ru.job4j.devops.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Результат вычислений */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    /** Значение */
    private double value;
}
