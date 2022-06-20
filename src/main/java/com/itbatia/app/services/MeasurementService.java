package com.itbatia.app.services;

import com.itbatia.app.models.Measurement;
import com.itbatia.app.repositories.MeasurementRepository;
import com.itbatia.app.repositories.SensorRepository;
import com.itbatia.app.util.customExceptions.MeasurementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public Measurement findById(int id) {
        return measurementRepository.findById(id).orElseThrow(MeasurementNotFoundException::new);
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        //Нахожу сенсор в БД и вставляю объект из Hibernate Persistent context:
        measurement.setSensor(sensorRepository.findByName(measurement.getSensor().getName()).orElse(null));

        measurement.setCreatedAt(LocalDateTime.now());
    }
}
