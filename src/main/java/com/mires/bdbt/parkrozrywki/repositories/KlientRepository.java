package com.mires.bdbt.parkrozrywki.repositories;

import com.mires.bdbt.parkrozrywki.entities.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long> {
    Klient findByLogin(String login);
}
