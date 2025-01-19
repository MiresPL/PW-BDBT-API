package com.mires.bdbt.parkrozrywki.services;

import com.mires.bdbt.parkrozrywki.entities.Poczta;
import com.mires.bdbt.parkrozrywki.repositories.PocztaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PocztaService {
    private final PocztaRepository pocztaRepository;

    public PocztaService(PocztaRepository pocztaRepository) {
        this.pocztaRepository = pocztaRepository;
    }

    public List<Poczta> findAll() {
        return pocztaRepository.findAll();
    }
}
