package com.mires.bdbt.parkrozrywki.services;

import com.mires.bdbt.parkrozrywki.entities.Bilet;
import com.mires.bdbt.parkrozrywki.repositories.BiletyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BiletyService {
    private final BiletyRepository biletyRepository;

    public BiletyService(BiletyRepository biletyRepository) {
        this.biletyRepository = biletyRepository;
    }

    public List<Bilet> findAll() {
        return biletyRepository.findAll();
    }

    public List<Bilet> getTicketsByKlient(Long id) {
        return biletyRepository.findBiletyByIdKlienta(id);
    }

    public Bilet findById(Long id) {
        return biletyRepository.findById(id).orElse(null);
    }

}
