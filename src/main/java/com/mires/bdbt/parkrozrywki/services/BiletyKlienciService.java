package com.mires.bdbt.parkrozrywki.services;

import com.mires.bdbt.parkrozrywki.entities.Bilet;
import com.mires.bdbt.parkrozrywki.entities.BiletyKlienci;
import com.mires.bdbt.parkrozrywki.repositories.BiletyKlienciRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BiletyKlienciService {
    private final BiletyKlienciRepository biletyKlienciRepository;

    public BiletyKlienciService(BiletyKlienciRepository biletyKlienciRepository) {
        this.biletyKlienciRepository = biletyKlienciRepository;
    }

    public List<BiletyKlienci> findAll() {
        return biletyKlienciRepository.findAll();
    }

    public BiletyKlienci findById(Long id) {
        return biletyKlienciRepository.findById(id).orElse(null);
    }

    public BiletyKlienci findByNrKlientaAndNrBiletu(Long nrKlienta, Long nrBiletu) {
        return biletyKlienciRepository.findByNrKlientaAndNrBiletu(nrKlienta, nrBiletu);
    }

    public void save(BiletyKlienci biletyKlienci) {
        biletyKlienciRepository.save(biletyKlienci);
    }

    public void remove(Long id) {
        biletyKlienciRepository.deleteById(id);
    }
}
