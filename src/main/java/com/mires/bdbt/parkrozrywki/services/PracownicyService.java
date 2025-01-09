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

    public List<Pracownik> getAllPracownicy() {
        return pracownicyRepository.getAllPracownikInfo();
    }
}
