package ru.job4j.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Основной класс для запуска приложения */
@SpringBootApplication
public class CalcApplication {

    /**
     * Основной метод для запуска приложения
     *
     * @param args массив аргументов
     */
    public static void main(String[] args) {
        SpringApplication.run(CalcApplication.class, args);
    }
}
