package com.mires.bdbt.parkrozrywki.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "PRACOWNICY")
@Getter
@Setter
public class Pracownik {
    @Id
    @Column(name = "NR_PRACOWNIKA")
    private Long nrPracownika;

    @Column(name = "IMIE", length = 20)
    private String imie;

    @Column(name = "NAZWISKO", length = 30)
    private String nazwisko;

    @Column(name = "PESEL", length = 11)
    private String pesel;

    @Column(name = "DATA_URODZENIA")
    private LocalDate dataUrodzenia;

    @Column(name = "NR_TELEFONU", length = 16)
    private String nrTelefonu;

    @Column(name = "EMAIL", length = 30)
    private String email;

    @Column(name = "STANOWISKO", length = 20)
    private String stanowisko;

    @Column(name = "NR_KONTA", length = 26)
    private String nrKonta;

    @Column(name = "DATA_ZATRUDNIENIA")
    private LocalDate dataZatrudnienia;

    @ManyToOne(fetch = FetchType.LAZY) // Use FetchType.LAZY to load related entities on demand
    @JoinColumn(name = "NR_PARKU") // Maps the foreign key
    private ParkRozrywki parkRozrywki;

    @ManyToOne(fetch = FetchType.LAZY) // Use FetchType.LAZY to load related entities on demand
    @JoinColumn(name = "NR_ADRESU") // Maps the foreign key
    private Adres adres;
}
