package com.mires.bdbt.parkrozrywki.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DOMY_STRACHOW")
@Getter
@Setter
public class DomStrachu {
    @Id
    @Column(name = "NR_ATRAKCJI")
    private Long nrAtrakcji;

    @Column(name = "LICZBA_AKTOROW")
    private Integer liczbaAktorow;

    @Column(name = "EFEKTY_SPECJALNE", length = 30)
    private String efektySpecjalne;

    @Column(name = "POZIOM_STRACHU", length = 10)
    private String poziomStrachu;
}
