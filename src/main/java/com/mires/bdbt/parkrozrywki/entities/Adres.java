package com.mires.bdbt.parkrozrywki.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ADRESY")
@Getter
@Setter
public class Adres {
    @Id
    @Column(name = "NR_ADRESU")
    private Long nrAdresu;

    @Column(name = "MIEJSCOWOSC", length = 30)
    private String miejscowosc;

    @Column(name = "ULICA", length = 30)
    private String ulica;

    @Column(name = "NR_DOMU", length = 5)
    private String nrDomu;

    @Column(name = "NR_LOKALU", length = 5)
    private String nrLokalu;

    @ManyToOne(fetch = FetchType.LAZY) // Use FetchType.LAZY to load related entities on demand
    @JoinColumn(name = "NR_POCZCTY") // Maps the foreign key
    private Poczta poczta;

    @OneToMany(mappedBy = "adres", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pracownik> pracownikList;
}