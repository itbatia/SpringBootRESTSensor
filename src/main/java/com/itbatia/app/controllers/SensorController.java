package com.itbatia.app.controllers;

import com.itbatia.app.DTO.SensorDTO;
import com.itbatia.app.DTO.SensorsResponse;
import com.itbatia.app.models.Sensor;
import com.itbatia.app.services.SensorService;
import com.itbatia.app.util.ErrorResponse;
import com.itbatia.app.util.customExceptions.SensorNotRegisteredException;
import com.itbatia.app.util.customExceptions.SensorNotFoundException;
import com.itbatia.app.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.itbatia.app.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping("/{id}")
    public SensorDTO getById(@PathVariable("id") int id) {
        return convertToSensorDTO(sensorService.findById(id));
    }

    @GetMapping
    public SensorsResponse getAllSensors() {
        //Оборачиваю список из элементов в один объект для пересылки:
        return new SensorsResponse(sensorService.findAll().stream()
                .map(this::convertToSensorDTO).collect(Collectors.toList()));
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        Sensor sensorToAdd = convertToSensor(sensorDTO);
        sensorValidator.validate(sensorToAdd, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new SensorNotRegisteredException(returnErrorsToClient(bindingResult));
        }

        sensorService.save(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotRegisteredException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Сенсор с таким id не зарегистрирован",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
