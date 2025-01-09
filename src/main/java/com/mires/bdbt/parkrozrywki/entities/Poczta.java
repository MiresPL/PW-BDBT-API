package com.mires.bdbt.parkrozrywki.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "POCZTY")
@Getter
@Setter
public class Poczta {
    @Id
    @Column(name = "NR_POCZCTY")
    private Long nrPoczty;

    @Column(name = "KOD_POCZTOWY", length = 6)
    private String kodPocztowy;

    @Column(name = "MIASTO_POCZTY", length = 30)
    private String miastoPoczty;

    @OneToMany(mappedBy = "poczta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Adres> adresList;
}
