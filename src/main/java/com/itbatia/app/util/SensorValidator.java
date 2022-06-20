package com.itbatia.app.util;

import com.itbatia.app.models.Sensor;
import com.itbatia.app.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Sensor sensor = (Sensor) target;

        if (sensorService.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name", "", "Сенсор с таким названием уже зарегистрирован");
        }
    }
}
