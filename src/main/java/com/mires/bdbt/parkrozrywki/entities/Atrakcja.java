package com.mires.bdbt.parkrozrywki.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ATRAKCJE")
@Getter
@Setter
public class Atrakcja {
    @Id
    @Column(name = "NR_ATRAKCJI")
    private Long nrAtrakcji;

    @Column(name = "NAZWA_ATRAKCJI", length = 30)
    private String nazwaAtrakcji;

    @Column(name = "MIN_WIEK")
    private Integer minWiek;

    @Column(name = "MIN_WZROST")
    private Integer minWzrost;

    @Column(name = "MAX_LICZBA_OSOB")
    private Integer maxLiczbaOsob;

    @Column(name = "NR_PARKU")
    private Long nrParku;

    @Column(name = "ZDJECIE", length = 1000)
    private String zdjecie;
}

