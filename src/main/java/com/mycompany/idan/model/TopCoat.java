package com.mycompany.idan.model;

public class TopCoat extends Layanan {

    private double harga;

    public TopCoat(double harga) {
        super("Top Coat");
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