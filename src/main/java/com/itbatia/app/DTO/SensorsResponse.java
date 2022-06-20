package com.itbatia.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SensorsResponse {
    private List<SensorDTO> sensors;
}
