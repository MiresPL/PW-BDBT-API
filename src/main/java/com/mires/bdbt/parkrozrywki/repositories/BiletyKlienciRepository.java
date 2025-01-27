package com.mires.bdbt.parkrozrywki.repositories;

import com.mires.bdbt.parkrozrywki.entities.Bilet;
import com.mires.bdbt.parkrozrywki.entities.BiletyKlienci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiletyKlienciRepository extends JpaRepository<BiletyKlienci, Long> {
    @Query("SELECT b FROM BiletyKlienci b WHERE b.nrKlienta = ?1 AND b.nrBiletu = ?2")
    List<BiletyKlienci> findByNrKlientaAndNrBiletu(Long nrKlienta, Long nrBiletu);
}
