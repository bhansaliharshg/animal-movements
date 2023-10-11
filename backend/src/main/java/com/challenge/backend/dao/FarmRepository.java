package com.challenge.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.backend.model.Farm;

@Repository
public interface FarmRepository extends JpaRepository<Farm, String>{
    
}
