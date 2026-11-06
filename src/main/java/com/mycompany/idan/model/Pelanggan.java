package com.mycompany.idan.model;

public class Pelanggan {

    private String nama;
    private String email;
    private String nomorTelepon;

    public Pelanggan(String nama, String email,
                     String nomorTelepon) {
        this.nama = nama;
        this.email = email;
        this.nomorTelepon = nomorTelepon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException(
                "Nama pelanggan tidak boleh kosong."
            );
        }
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }
}