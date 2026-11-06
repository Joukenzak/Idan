package com.mycompany.idan.model;

public abstract class Layanan {

    private String namaLayanan;

    public Layanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        if (namaLayanan == null || namaLayanan.isBlank()) {
            throw new IllegalArgumentException(
                "Nama layanan tidak boleh kosong."
            );
        }
        this.namaLayanan = namaLayanan;
    }

    public abstract double hitungBiaya();

    public void tampilkanInfo() {
        System.out.println(
            namaLayanan + " : Rp"
            + String.format("%,.0f", hitungBiaya())
        );
    }
}