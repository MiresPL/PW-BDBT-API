package com.mires.bdbt.parkrozrywki.repositories;

import com.mires.bdbt.parkrozrywki.entities.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracownicyRepository extends JpaRepository<Pracownik, Long> {
    @Query("SELECT p FROM Pracownik p " +
            "JOIN FETCH p.parkRozrywki " +
            "JOIN FETCH p.adres a " +
            "JOIN FETCH a.poczta"
    )
    List<Pracownik> getAllPracownikInfo();
}
