package ru.job4j.devops.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.job4j.devops.enums.CalcEventTypes;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CALC_EVENTS")
public class CalcEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private CalcEventTypes type;

    @Column(name = "FIRST_ARG")
    private Double firstArg;

    @Column(name = "SECOND_ARG")
    private Double secondArg;

    @Column(name = "RESULT")
    private Double result;

    @Setter(AccessLevel.PRIVATE)
    @Column(name = "CREATE_DATE", insertable = false, updatable = false)
    private LocalDate createDate;
}
