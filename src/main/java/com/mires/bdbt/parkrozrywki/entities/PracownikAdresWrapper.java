package com.mires.bdbt.parkrozrywki.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PracownikAdresWrapper {
    private Pracownik pracownik;
    private Adres adres;
}
