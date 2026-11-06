package com.mycompany.idan.service;

import java.util.List;
import com.mycompany.idan.model.CleanBuild;
import com.mycompany.idan.model.Decal;
import com.mycompany.idan.model.PanelLine;
import com.mycompany.idan.model.Pelanggan;
import com.mycompany.idan.model.Pesanan;
import com.mycompany.idan.model.TopCoat;
import com.mycompany.idan.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PemesananService {

    public Pesanan buatContohPesanan() {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            // Membuat objek pelanggan
            Pelanggan pelanggan = new Pelanggan(
                "Wildan",
                "wildan@example.com",
                "081234567890"
            );

            // Menyimpan pelanggan terlebih dahulu
            session.persist(pelanggan);

            // Membuat objek pesanan
            Pesanan pesanan = new Pesanan(
                "IM-001",
                pelanggan,
                "RX-78-2 Gundam"
            );

            // Menambahkan layanan ke pesanan
            pesanan.tambahLayanan(new CleanBuild("HG"));
            pesanan.tambahLayanan(new PanelLine(20000));
            pesanan.tambahLayanan(new TopCoat(25000));
            pesanan.tambahLayanan(new Decal(15000));

            // Menyimpan pesanan beserta daftar layanannya
            session.persist(pesanan);

            // Menyelesaikan transaksi
            transaction.commit();

            System.out.println("Data pesanan berhasil disimpan!");

            return pesanan;

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Gagal menyimpan data pesanan!");
            e.printStackTrace();

            return null;
        }
    }
    
    public void tampilkanSemuaPesanan() {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            List<Pesanan> daftarPesanan = session.createQuery(
                    "SELECT DISTINCT p FROM Pesanan p "
                    + "JOIN FETCH p.pelanggan "
                    + "LEFT JOIN FETCH p.daftarLayanan",
                    Pesanan.class
            ).getResultList();

            if (daftarPesanan.isEmpty()) {
                System.out.println("Belum ada data pesanan.");
                return;
            }

            System.out.println("\n==================================");
            System.out.println("       DAFTAR SELURUH PESANAN");
            System.out.println("==================================");

            for (Pesanan pesanan : daftarPesanan) {
                pesanan.tampilkanStruk();
            }

            System.out.println("\nJumlah pesanan: " + daftarPesanan.size());

        } catch (Exception e) {
            System.out.println("Gagal mengambil data pesanan!");
            e.printStackTrace();
        }
    }
}
