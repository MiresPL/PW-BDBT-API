package com.mires.bdbt.parkrozrywki.services;

import com.mires.bdbt.parkrozrywki.entities.Wlasciciel;
import com.mires.bdbt.parkrozrywki.repositories.WlascicielRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WlascicielService {
    private final WlascicielRepository wlascicielRepository;
    private final PasswordEncoder passwordEncoder;

    public WlascicielService(WlascicielRepository wlascicielRepository, PasswordEncoder passwordEncoder) {
        this.wlascicielRepository = wlascicielRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Wlasciciel login(final String login, final String password) {
        Wlasciciel wlasciciel = wlascicielRepository.findByLogin(login);
        if (wlasciciel != null && passwordEncoder.matches(password, wlasciciel.getHaslo())) return wlasciciel;
        return null;
    }

    public Wlasciciel findById(Long id) {
        return wlascicielRepository.findById(id).orElse(null);
    }

    public void save(Wlasciciel wlasciciel) {
        wlascicielRepository.save(wlasciciel);
    }

    public void remove(Long id) {
        wlascicielRepository.deleteById(id);
    }
}
