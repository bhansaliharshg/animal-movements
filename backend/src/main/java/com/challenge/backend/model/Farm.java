package com.challenge.backend.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Farm")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Farm implements Serializable{

    @Id
    private String premiseId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private float latitude;
    private float longitude;
    private int totalAnimals;

}
