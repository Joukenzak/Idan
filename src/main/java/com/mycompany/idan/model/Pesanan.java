package com.mycompany.idan.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pesanan {

    private String nomorPesanan;
    private Pelanggan pelanggan;
    private String namaGunpla;
    private LocalDateTime tanggalPesanan;
    private String status;

    private final List<Layanan> daftarLayanan;

    public Pesanan(String nomorPesanan,
                   Pelanggan pelanggan,
                   String namaGunpla) {

        this.nomorPesanan = nomorPesanan;
        this.pelanggan = pelanggan;
        this.namaGunpla = namaGunpla;
        this.tanggalPesanan = LocalDateTime.now();
        this.status = "Menunggu Konfirmasi";
        this.daftarLayanan = new ArrayList<>();
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