package com.mycompany.idan.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "pesanan")
public class Pesanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomor_pesanan", nullable = false, unique = true)
    private String nomorPesanan;

    @ManyToOne
    @JoinColumn(name = "pelanggan_id", nullable = false)
    private Pelanggan pelanggan;

    @Column(name = "nama_gunpla", nullable = false)
    private String namaGunpla;

    @Column(name = "tanggal_pesanan")
    private LocalDateTime tanggalPesanan;

    @Column(name = "status")
    private String status;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "pesanan_id")
    private List<Layanan> daftarLayanan = new ArrayList<>();

    // Constructor untuk Hibernate
    protected Pesanan() {
    }

    // Constructor untuk pembuatan objek biasa
    public Pesanan(String nomorPesanan,
                   Pelanggan pelanggan,
                   String namaGunpla) {

        this.nomorPesanan = nomorPesanan;
        setPelanggan(pelanggan);
        this.namaGunpla = namaGunpla;
        this.tanggalPesanan = LocalDateTime.now();
        this.status = "Menunggu Konfirmasi";
    }

    public Long getId() {
        return id;
    }

    public void tambahLayanan(Layanan layanan) {
        if (layanan == null) {
            throw new IllegalArgumentException(
                "Layanan tidak boleh kosong."
            );
        }

        daftarLayanan.add(layanan);
    }

    public double hitungTotal() {
        double total = 0;

        for (Layanan layanan : daftarLayanan) {
            total += layanan.hitungBiaya();
        }

        return total;
    }

    public void tampilkanStruk() {
        System.out.println("\n==================================");
        System.out.println("          IDANMERAKIT");
        System.out.println("       STRUK PESANAN JASA");
        System.out.println("==================================");
        System.out.println("Nomor    : " + nomorPesanan);
        System.out.println("Pelanggan: " + pelanggan.getNama());
        System.out.println("Gunpla   : " + namaGunpla);
        System.out.println("Tanggal  : " + tanggalPesanan);
        System.out.println("Status   : " + status);
        System.out.println("----------------------------------");

        for (Layanan layanan : daftarLayanan) {
            layanan.tampilkanInfo();
        }

        System.out.println("----------------------------------");
        System.out.printf(
            "TOTAL BIAYA: Rp%,.0f%n", hitungTotal()
        );
        System.out.println("==================================");
    }

    public String getNomorPesanan() {
        return nomorPesanan;
    }

    public void setNomorPesanan(String nomorPesanan) {
        this.nomorPesanan = nomorPesanan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        if (pelanggan == null) {
            throw new IllegalArgumentException(
                "Pelanggan harus diisi."
            );
        }
        this.pelanggan = pelanggan;
    }

    public String getNamaGunpla() {
        return namaGunpla;
    }

    public void setNamaGunpla(String namaGunpla) {
        this.namaGunpla = namaGunpla;
    }

    public LocalDateTime getTanggalPesanan() {
        return tanggalPesanan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException(
                "Status pesanan harus diisi."
            );
        }
        this.status = status;
    }

    public List<Layanan> getDaftarLayanan() {
        return Collections.unmodifiableList(daftarLayanan);
    }
}
