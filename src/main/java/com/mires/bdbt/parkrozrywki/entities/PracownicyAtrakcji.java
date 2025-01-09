package com.mires.bdbt.parkrozrywki.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRACOWNICY_ATRAKCJE")
@Getter
@Setter
public class PracownicyAtrakcji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NR_PRACOWNIKA")
    private Long nrPracownika;

    @Column(name = "NR_ATRAKCJI")
    private Long nrAtrakcji;
}
