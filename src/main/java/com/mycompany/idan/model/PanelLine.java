
package com.mycompany.idan.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PANEL_LINE")
public class PanelLine extends Layanan {

    // Constructor untuk Hibernate
    protected PanelLine() {
    }

    public PanelLine(double harga) {
        super("Panel Line");
        setHarga(harga);
    }

    @Override
    public double hitungBiaya() {
        return getHarga();
    }
}
