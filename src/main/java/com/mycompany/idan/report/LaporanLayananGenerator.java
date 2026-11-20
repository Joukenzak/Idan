package com.mycompany.idan.report;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
// Jika memakai JasperReports 6.x, ganti import ini menjadi:
// net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.pdf.JRPdfExporter;

/**
 * Mengisi template laporan dengan data lalu mengekspornya ke PDF.
 *
 * Template dicari di classpath (folder src/main/resources/reports):
 *   1. /reports/laporan_layanan.jasper  (hasil compile dari Jaspersoft Studio), atau
 *   2. /reports/laporan_layanan.jrxml   (dikompilasi saat dijalankan)
 */
public class LaporanLayananGenerator {

    private static final String TEMPLATE_JASPER = "/reports/laporan_layanan.jasper";
    private static final String TEMPLATE_JRXML = "/reports/laporan_layanan.jrxml";

    public void eksporPdf(List<LayananReportDto> data, Path tujuan)
            throws JRException, IOException {

        JasperReport report = muatTemplate();

        // Parameter opsional: hanya terpakai jika dideklarasikan di .jrxml
        // (parameter yang tidak dideklarasikan akan diabaikan).
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("NAMA_USAHA", "IdanMerakit");
        parameter.put("TANGGAL_CETAK", new Date());

        JasperPrint print = JasperFillManager.fillReport(
                report,
                parameter,
                new JRBeanCollectionDataSource(data)
        );

        Path folder = tujuan.toAbsolutePath().getParent();
        if (folder != null) {
            Files.createDirectories(folder);
        }

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(
                new SimpleOutputStreamExporterOutput(tujuan.toFile())
        );
        exporter.exportReport();
    }

    private JasperReport muatTemplate() throws JRException, IOException {

        try (InputStream jasper = getClass().getResourceAsStream(TEMPLATE_JASPER)) {
            if (jasper != null) {
                return (JasperReport) JRLoader.loadObject(jasper);
            }
        }

        try (InputStream jrxml = getClass().getResourceAsStream(TEMPLATE_JRXML)) {
            if (jrxml != null) {
                return JasperCompileManager.compileReport(jrxml);
            }
        }

        throw new IllegalStateException(
                "Template laporan tidak ditemukan. Letakkan laporan_layanan.jrxml "
                + "(atau laporan_layanan.jasper) di src/main/resources/reports/."
        );
    }
}
