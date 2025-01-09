package com.mires.bdbt.parkrozrywki.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "WLASCICIELE")
@Getter
@Setter
public class Wlasciciel {
    @Id
    @Column(name = "NR_WLASCICIELA")
    private Long nrWlasciciela;

    @Column(name = "IMIE", length = 20)
    private String imie;

    @Column(name = "NAZWISKO", length = 30)
    private String nazwisko;

    @Column(name = "DATA_URODZENIA")
    private LocalDate dataUrodzenia;

    @Column(name = "NR_PARKU")
    private Long nrParku;

    @Column(name = "NR_ADRESU")
    private Long nrAdresu;
}
