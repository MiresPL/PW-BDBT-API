package com.mires.bdbt.parkrozrywki.services;

import com.mires.bdbt.parkrozrywki.entities.Adres;
import com.mires.bdbt.parkrozrywki.repositories.AdressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdressService {
    private final AdressRepository adressRepository;

    public AdressService(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    public List<Adres> findAll() {
        return adressRepository.findAll();
    }

    public Long getNextId() {
        return adressRepository.findAll().stream().mapToLong(Adres::getNrAdresu).max().orElse(0) + 1;
    }

    public Adres findById(Long id) {
        return adressRepository.findById(id).orElse(null);
    }

    public Adres save(Adres adres) {
        return adressRepository.save(adres);
    }

    public void remove(Long id) {
        adressRepository.deleteById(id);
    }

}
