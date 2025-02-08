package ru.job4j.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс для запуска приложения
 */
@SpringBootApplication
public class CalcApplication {

    /**
     * Точка входа в приложение
     *
     * @param args аргументы
     */
    public static void main(String[] args) {
        SpringApplication.run(CalcApplication.class, args);
    }
}
