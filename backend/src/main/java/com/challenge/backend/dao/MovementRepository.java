package com.challenge.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.backend.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer>{
    
}
