package com.mires.bdbt.parkrozrywki.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "PARKI_ROZRYWKI")
@Getter
@Setter
public class ParkRozrywki {
    @Id
    @Column(name = "NR_PARKU")
    private Long nrParku;

    @Column(name = "NAZWA", length = 20)
    private String nazwa;

    @Column(name = "NR_TELEFONU", length = 16)
    private String nrTelefonu;

    @Column(name = "EMAIL", length = 30)
    private String email;

    @Column(name = "NR_ADRESU")
    private Long nrAdresu;

    @OneToMany(mappedBy = "parkRozrywki", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pracownik> pracownikList;
}
