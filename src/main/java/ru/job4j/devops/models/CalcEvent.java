package ru.job4j.devops.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.job4j.devops.CalcEventTypes;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "CALC_EVENT")
public class CalcEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FIRST_ARG")
    private Double first;

    @Column(name = "SECOND_ARG")
    private Double second;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private CalcEventTypes type;

    @Column(name = "RESULT")
    private Double result;

    @Setter(AccessLevel.PRIVATE)
    @Column(name = "CREATE_DATE", insertable = false, updatable = false)
    private LocalDate createDate;
}
