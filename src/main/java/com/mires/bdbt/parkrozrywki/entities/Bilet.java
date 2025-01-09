package com.mires.bdbt.parkrozrywki.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "BILETY")
@Getter
@Setter
public class Bilet {
    @Id
    @Column(name = "NR_BILETU")
    private Long nrBiletu;

    @Column(name = "RODZAJ_BILETU", length = 30)
    private String rodzajBiletu;

    @Column(name = "CENA")
    private BigDecimal cena;

    @Column(name = "NR_PARKU")
    private Long nrParku;
}
