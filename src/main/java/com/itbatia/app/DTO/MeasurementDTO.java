package com.itbatia.app.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MeasurementDTO {

    @NotNull(message = "Показания температуры воздуха не могут быть пустыми")
    @Min(value = -100, message = "Температура воздуха не может быть ниже -100 градусов")
    @Max(value = 100, message = "Температура воздуха не может быть выше 100 градусов")
    private Double value;

    @NotNull(message = "Отсутствует значение raining")
    private Boolean raining;

    @NotNull(message = "Отсутствует название сенсора")
    private SensorDTO sensor;
}
