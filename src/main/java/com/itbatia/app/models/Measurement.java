package com.itbatia.app.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
@Data
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    @Min(value = -100, message = "Температура воздуха не может быть ниже -100 градусов")
    @Max(value = 100, message = "Температура воздуха не может быть выше 100 градусов")
    @NotNull(message = "Показания температуры воздуха не могут быть пустыми")
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "Отсутствует значение raining")
    private Boolean raining;

    @ManyToOne()
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    @NotNull(message = "Отсутствует название сенсора, которому принадлежат текущие измерения")
    private Sensor sensor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
