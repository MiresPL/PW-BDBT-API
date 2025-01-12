package com.mires.bdbt.parkrozrywki.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Entity
@Table(name = "BILETY_KLIENCI")
@Getter
@Setter
public class BiletyKlienci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NR_BILETU")
    private Long nrBiletu;

    @Column(name = "NR_KLIENTA")
    private Long nrKlienta;

    @Column(name = "DATA_ZAKUPU")
    private Date dataZakupu;

    @Column(name = "DATA_WAZNOSCI")
    private Date dataWaznosci;
}
