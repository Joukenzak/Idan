
package com.mycompany.idan.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DECAL")
public class Decal extends Layanan {

    // Constructor untuk Hibernate
    protected Decal() {
    }

    public Decal(double harga) {
        super("Decal");
        setHarga(harga);
    }

    @Override
    public double hitungBiaya() {
        return getHarga();
    }
}
