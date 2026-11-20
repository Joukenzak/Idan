package com.mycompany.idan;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.nio.file.Path;
import java.util.List;

import com.mycompany.idan.report.LaporanLayananGenerator;
import com.mycompany.idan.report.LayananReportDto;
import com.mycompany.idan.service.LaporanLayananService;
import com.mycompany.idan.util.HibernateUtil;

/**
 * Menjalankan laporan "Daftar Detail Layanan" dari database ke PDF.
 * Argumen opsional: lokasi file PDF (default: laporan_layanan.pdf).
 */
public class CetakLaporanLayanan {

    public static void main(String[] args) {

        Path keluaran = Path.of(
                args.length > 0 ? args[0] : "laporan_layanan.pdf"
        );

        try {
            List<LayananReportDto> data =
                    new LaporanLayananService().ambilDaftarLayanan();

            if (data.isEmpty()) {
                System.out.println(
                        "Belum ada data layanan di database; laporan akan kosong."
                );
            }

            new LaporanLayananGenerator().eksporPdf(data, keluaran);

            System.out.println(
                    "Laporan tersimpan: " + keluaran.toAbsolutePath()
            );

            if (!GraphicsEnvironment.isHeadless()
                    && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(keluaran.toFile());
            }

        } catch (Exception e) {
            System.out.println("Gagal membuat laporan layanan!");
            e.printStackTrace();

        } finally {
            HibernateUtil.shutdown();
        }
    }
}
