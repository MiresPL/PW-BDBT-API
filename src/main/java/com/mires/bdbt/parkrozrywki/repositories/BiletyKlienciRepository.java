package com.mires.bdbt.parkrozrywki.repositories;

import com.mires.bdbt.parkrozrywki.entities.Bilet;
import com.mires.bdbt.parkrozrywki.entities.BiletyKlienci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiletyKlienciRepository extends JpaRepository<BiletyKlienci, Long> {
    List<Bilet> findTicketsByNrKlienta(Long nrKlienta);
}
