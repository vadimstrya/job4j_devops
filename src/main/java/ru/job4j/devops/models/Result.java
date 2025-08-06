package ru.job4j.devops.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.job4j.devops.enums.CalcOperations;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "ru.job4j.devops.models.Result")
@Table(name = "RESULTS")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "FIRST_ARG")
    private Double firstArg;

    @Column(name = "SECOND_ARG")
    private Double secondArg;

    @Column(name = "RESULT")
    private Double result;

    @Column(name = "OPERATION")
    @Enumerated(EnumType.STRING)
    private CalcOperations operation;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;
}
