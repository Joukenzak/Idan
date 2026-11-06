
package com.mycompany.idan.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TOP_COAT")
public class TopCoat extends Layanan {

    // Constructor untuk Hibernate
    protected TopCoat() {
    }

    public TopCoat(double harga) {
        super("Top Coat");
        setHarga(harga);
    }

    @Override
    public double hitungBiaya() {
        return getHarga();
    }
}
