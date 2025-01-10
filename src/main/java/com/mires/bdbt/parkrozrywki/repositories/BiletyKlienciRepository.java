package com.mires.bdbt.parkrozrywki.repositories;

import com.mires.bdbt.parkrozrywki.entities.BiletyKlienci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiletyKlienciRepository extends JpaRepository<BiletyKlienci, Long> {
}
