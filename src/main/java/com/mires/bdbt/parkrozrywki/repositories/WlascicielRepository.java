package com.mires.bdbt.parkrozrywki.repositories;

import com.mires.bdbt.parkrozrywki.entities.Wlasciciel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WlascicielRepository extends JpaRepository<Wlasciciel, Long> {
    Wlasciciel findByLogin(String login);
}
