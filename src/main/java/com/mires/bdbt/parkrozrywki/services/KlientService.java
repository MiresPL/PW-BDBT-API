package com.mires.bdbt.parkrozrywki.services;

import com.mires.bdbt.parkrozrywki.entities.Klient;
import com.mires.bdbt.parkrozrywki.repositories.KlientRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class KlientService {
    private final KlientRepository klientRepository;
    private final PasswordEncoder passwordEncoder;

    public KlientService(KlientRepository klientRepository, PasswordEncoder passwordEncoder) {
        this.klientRepository = klientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Klient login(final String login, final String password) {
        Klient klient = klientRepository.findByLogin(login);
        System.out.println(passwordEncoder.encode(klient.getHaslo()));
        if (klient != null && passwordEncoder.matches(password, klient.getHaslo())) return klient;
        return null;
    }

    public List<Klient> findAll() {
        return klientRepository.findAll();
    }

    public Klient findById(Long id) {
        return klientRepository.findById(id).orElse(null);
    }

    public void save(Klient klient) {
        klient.setHaslo(passwordEncoder.encode(klient.getHaslo()));
        klientRepository.save(klient);
    }

    public void remove(Long id) {
        klientRepository.deleteById(id);
    }
}
