package com.mires.bdbt.parkrozrywki.services;

import com.mires.bdbt.parkrozrywki.entities.Atrakcja;
import com.mires.bdbt.parkrozrywki.repositories.AtrakcjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtrakcjaService {
    private final AtrakcjaRepository atrakcjaRepository;

    public AtrakcjaService(AtrakcjaRepository atrakcjaRepository) {
        this.atrakcjaRepository = atrakcjaRepository;
    }

    public List<Atrakcja> findAll() {
        return atrakcjaRepository.findAll();
    }
}