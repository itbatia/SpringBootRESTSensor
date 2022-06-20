package com.itbatia.app.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "Sensor")
@Data
public class Sensor implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Название сенсора не может быть пустым")
    @Size(min = 3, max = 30, message = "Название сенсора должно быть от 3 до 30 символов")
    private String name;
}
