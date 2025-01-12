package com.mires.bdbt.parkrozrywki.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "KLIENCI")
@Getter
@Setter
public class Klient {
    @Id
    @Column(name = "NR_KLIENTA")
    private Long nrKlienta;

    @Column(name = "LOGIN", length = 20)
    private String login;

    @Column(name = "HASLO", length = 1000)
    private String haslo;

    @Column(name = "IMIE", length = 20)
    private String imie;

    @Column(name = "NAZWISKO", length = 30)
    private String nazwisko;

    @Column(name = "DATA_URODZENIA")
    private Date dataUrodzenia;

    @Column(name = "EMAIL", length = 30)
    private String email;

    @Column(name = "NR_TELEFONU", length = 16)
    private String nrTelefonu;

    @Column(name = "NR_PARKU")
    private Long nrParku;
}
