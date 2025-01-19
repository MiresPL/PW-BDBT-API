package com.mires.bdbt.parkrozrywki.repositories;

import com.mires.bdbt.parkrozrywki.entities.Poczta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PocztaRepository extends JpaRepository<Poczta, Long> {
    @Query("SELECT p FROM Poczta p")
    List<Poczta> findAll();
}
