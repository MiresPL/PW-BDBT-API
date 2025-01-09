package com.mires.bdbt.parkrozrywki.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "KOLEJKI")
@Getter
@Setter
public class Kolejka {
    @Id
    @Column(name = "NR_ATRAKCJI")
    private Long nrAtrakcji;

    @Column(name = "MAX_WYSOKOSC")
    private Integer maxWysokosc;

    @Column(name = "MAX_PREDKOSC")
    private Integer maxPredkosc;

    @Column(name = "LICZBA_WAGONIKOW")
    private Integer liczbaWagonikow;

    @Column(name = "RODZAJ_KOLEJKI", length = 12)
    private String rodzajKolejki;
}
