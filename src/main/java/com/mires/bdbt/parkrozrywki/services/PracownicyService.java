package com.mires.bdbt.parkrozrywki.services;

import com.mires.bdbt.parkrozrywki.entities.Pracownik;
import com.mires.bdbt.parkrozrywki.repositories.PracownicyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PracownicyService {
    private final PracownicyRepository pracownicyRepository;

    public PracownicyService(PracownicyRepository pracownicyRepository) {
        this.pracownicyRepository = pracownicyRepository;
    }

    public Pracownik findById(final Long id) {
        return pracownicyRepository.findById(id).orElse(null);
    }

    public List<Pracownik> getAllPracownicy() {
        return pracownicyRepository.getAllPracownikInfo();
    }

    public void save(final Pracownik pracownik) {
        this.pracownicyRepository.save(pracownik);
    }

    public void remove(final Long id) {
        this.pracownicyRepository.deleteById(id);
    }
}
