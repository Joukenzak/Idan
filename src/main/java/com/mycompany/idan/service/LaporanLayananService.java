package com.mycompany.idan.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.mycompany.idan.model.CleanBuild;
import com.mycompany.idan.model.Layanan;
import com.mycompany.idan.model.Pesanan;
import com.mycompany.idan.report.LayananReportDto;
import com.mycompany.idan.util.HibernateUtil;

/**
 * Mengambil data untuk laporan "Daftar Detail Layanan":
 * satu baris per layanan pada setiap pesanan.
 */
public class LaporanLayananService {

    public List<LayananReportDto> ambilDaftarLayanan() {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            // JOIN FETCH supaya pelanggan dan daftarLayanan sudah
            // terisi sebelum session ditutup (hindari LazyInitializationException).
            List<Pesanan> daftarPesanan = session.createQuery(
                    "SELECT DISTINCT p FROM Pesanan p "
                    + "JOIN FETCH p.pelanggan "
                    + "LEFT JOIN FETCH p.daftarLayanan "
                    + "ORDER BY p.tanggalPesanan, p.id",
                    Pesanan.class
            ).getResultList();

            List<LayananReportDto> hasil = new ArrayList<>();

            for (Pesanan pesanan : daftarPesanan) {

                Date tanggal = pesanan.getTanggalPesanan() == null
                        ? null
                        : Timestamp.valueOf(pesanan.getTanggalPesanan());

                for (Layanan layanan : pesanan.getDaftarLayanan()) {

                    // Detail tambahan khusus Clean Build (grade Gunpla)
                    String keterangan = "-";
                    if (layanan instanceof CleanBuild cleanBuild) {
                        keterangan = "Grade " + cleanBuild.getGrade();
                    }

                    hasil.add(new LayananReportDto(
                            pesanan.getNomorPesanan(),
                            tanggal,
                            pesanan.getPelanggan().getNama(),
                            pesanan.getNamaGunpla(),
                            pesanan.getStatus(),
                            layanan.getNamaLayanan(),
                            keterangan,
                            // hitungBiaya(), bukan getHarga(): harga Clean Build
                            // dihitung dari grade dan kolom harganya kosong.
                            layanan.hitungBiaya()
                    ));
                }
            }

            return hasil;
        }
    }
}
