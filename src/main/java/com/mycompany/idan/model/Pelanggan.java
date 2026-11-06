package com.mycompany.idan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "pelanggan")
public class Pelanggan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "email")
    private String email;

    @Column(name = "nomor_telepon")
    private String nomorTelepon;

    // Constructor untuk Hibernate
    protected Pelanggan() {
    }

    // Constructor untuk pembuatan objek biasa
    public Pelanggan(String nama, String email,
                     String nomorTelepon) {
        setNama(nama);
        this.email = email;
        this.nomorTelepon = nomorTelepon;
    }

    public Long getId() {
        return id;
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
