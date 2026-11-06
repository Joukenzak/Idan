
package com.mycompany.idan.model;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "layanan")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "jenis_layanan",
    discriminatorType = DiscriminatorType.STRING
)
public abstract class Layanan {

    // Primary Key untuk Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_layanan", nullable = false)
    private String namaLayanan;

    @Column(name = "harga")
    private Double harga;

    // Constructor untuk Hibernate
    protected Layanan() {
    }

    public Layanan(String namaLayanan) {
        setNamaLayanan(namaLayanan);
    }

    // Getter ID
    public Long getId() {
        return id;
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

    public double getHarga() {
        return harga == null ? 0 : harga;
    }

    public void setHarga(double harga) {
        if (harga < 0) {
            throw new IllegalArgumentException(
                "Harga tidak boleh negatif."
            );
        }
        this.harga = harga;
    }

    public abstract double hitungBiaya();

    public void tampilkanInfo() {
        System.out.println(
            namaLayanan + " : Rp"
            + String.format("%,.0f", hitungBiaya())
        );
    }
}
