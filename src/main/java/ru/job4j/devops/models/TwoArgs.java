package ru.job4j.devops.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Дто с двумя аргументами */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwoArgs {
    private double first;
    private double second;
}
