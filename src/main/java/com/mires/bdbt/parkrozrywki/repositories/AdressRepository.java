package com.mires.bdbt.parkrozrywki.repositories;

import com.mires.bdbt.parkrozrywki.entities.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdressRepository extends JpaRepository<Adres, Long> {
    @Query("SELECT a FROM Adres a JOIN FETCH a.poczta")
    List<Adres> findAll();
}
