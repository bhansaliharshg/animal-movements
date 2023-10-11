package com.challenge.backend.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movement implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "account")
    private String account;

    @Column(name = "movementReason")
    private String movementReason;

    @Column(name = "species")
    private String species;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_premiseId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Farm origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_premiseId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Farm destination;

    @Column(name = "numberOfItemsMoved")
    private int numberOfItemsMoved;

    @Column(name = "shipmentDate")
    private String shipmentDate;

}
