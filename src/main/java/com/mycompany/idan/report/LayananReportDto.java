package com.mycompany.idan.report;

import java.util.Date;

/**
 * Satu baris pada laporan "Daftar Detail Layanan".
 *
 * Sengaja berupa kelas biasa dengan getter (bukan record) karena
 * JRBeanCollectionDataSource membaca field laporan lewat getter
 * bergaya JavaBean: field "namaPelanggan" di Jaspersoft Studio
 * dibaca dari getNamaPelanggan().
 *
 * Nama field untuk Jaspersoft Studio:
 *   nomorPesanan     (java.lang.String)
 *   tanggalPesanan   (java.util.Date)
 *   namaPelanggan    (java.lang.String)
 *   namaGunpla       (java.lang.String)
 *   status           (java.lang.String)
 *   jenisLayanan     (java.lang.String)
 *   keterangan       (java.lang.String)
 *   biaya            (java.lang.Double)
 */
public class LayananReportDto {

    private final String nomorPesanan;
    private final Date tanggalPesanan;
    private final String namaPelanggan;
    private final String namaGunpla;
    private final String status;
    private final String jenisLayanan;
    private final String keterangan;
    private final Double biaya;

    public LayananReportDto(String nomorPesanan,
                            Date tanggalPesanan,
                            String namaPelanggan,
                            String namaGunpla,
                            String status,
                            String jenisLayanan,
                            String keterangan,
                            Double biaya) {
        this.nomorPesanan = nomorPesanan;
        this.tanggalPesanan = tanggalPesanan;
        this.namaPelanggan = namaPelanggan;
        this.namaGunpla = namaGunpla;
        this.status = status;
        this.jenisLayanan = jenisLayanan;
        this.keterangan = keterangan;
        this.biaya = biaya;
    }

    public String getNomorPesanan() {
        return nomorPesanan;
    }

    public Date getTanggalPesanan() {
        return tanggalPesanan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public String getNamaGunpla() {
        return namaGunpla;
    }

    public String getStatus() {
        return status;
    }

    public String getJenisLayanan() {
        return jenisLayanan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public Double getBiaya() {
        return biaya;
    }
}
