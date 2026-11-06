package com.mycompany.idan.model;

public class PanelLine extends Layanan {

    private double harga;

    public PanelLine(double harga) {
        super("Panel Line");
        setHarga(harga);
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga < 0) {
            throw new IllegalArgumentException(
                "Harga tidak boleh negatif."
            );
        }
        this.harga = harga;
    }

    @Override
    public double hitungBiaya() {
        return harga;
    }
}