package com.mires.bdbt.parkrozrywki.repositories;

import com.mires.bdbt.parkrozrywki.entities.Bilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiletyRepository extends JpaRepository<Bilet, Long> {
    @Query("SELECT b FROM Bilet b")
    List<Bilet> findAll();

    @Query("SELECT b FROM Bilet b JOIN BiletyKlienci bk ON b.nrBiletu = bk.nrBiletu WHERE bk.nrKlienta = :idKlienta")
    List<Bilet> findBiletyByIdKlienta(@Param("idKlienta") Long idKlienta);
}
