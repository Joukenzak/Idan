package com.mycompany.idan;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycompany.idan.model.CleanBuild;
import com.mycompany.idan.model.Decal;
import com.mycompany.idan.model.PanelLine;
import com.mycompany.idan.model.Pelanggan;
import com.mycompany.idan.model.Pesanan;
import com.mycompany.idan.model.TopCoat;
import com.mycompany.idan.util.HibernateUtil;

/**
 * Mengisi beberapa pesanan contoh supaya laporan ada isinya.
 * Aman dijalankan berulang kali: pesanan dengan nomor yang sudah
 * ada di database dilewati.
 */
public class IsiDataContoh {

    public static void main(String[] args) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            int ditambah = 0;

            // IM-001
            if (!sudahAda(session, "IM-001")) {
                Pelanggan pelanggan = new Pelanggan(
                        "Wildan", "wildan@example.com", "081234567890");
                session.persist(pelanggan);

                Pesanan pesanan = new Pesanan(
                        "IM-001", pelanggan, "RX-78-2 Gundam");
                pesanan.tambahLayanan(new CleanBuild("HG"));
                pesanan.tambahLayanan(new PanelLine(20000));
                pesanan.tambahLayanan(new TopCoat(25000));
                pesanan.tambahLayanan(new Decal(15000));
                session.persist(pesanan);
                ditambah++;
            }

            // IM-002
            if (!sudahAda(session, "IM-002")) {
                Pelanggan pelanggan = new Pelanggan(
                        "Rina Sari", "rina@example.com", "081200000002");
                session.persist(pelanggan);

                Pesanan pesanan = new Pesanan(
                        "IM-002", pelanggan, "MG Freedom Gundam");
                pesanan.setStatus("Dikerjakan");
                pesanan.tambahLayanan(new CleanBuild("MG"));
                pesanan.tambahLayanan(new PanelLine(40000));
                pesanan.tambahLayanan(new TopCoat(50000));
                session.persist(pesanan);
                ditambah++;
            }

            // IM-003
            if (!sudahAda(session, "IM-003")) {
                Pelanggan pelanggan = new Pelanggan(
                        "Budi Santoso", "budi@example.com", "081200000003");
                session.persist(pelanggan);

                Pesanan pesanan = new Pesanan(
                        "IM-003", pelanggan, "HG Zaku II");
                pesanan.tambahLayanan(new CleanBuild("HG"));
                pesanan.tambahLayanan(new Decal(15000));
                session.persist(pesanan);
                ditambah++;
            }

            // IM-004
            if (!sudahAda(session, "IM-004")) {
                Pelanggan pelanggan = new Pelanggan(
                        "Dewi Lestari", "dewi@example.com", "081200000004");
                session.persist(pelanggan);

                Pesanan pesanan = new Pesanan(
                        "IM-004", pelanggan, "RG Nu Gundam");
                pesanan.setStatus("Selesai");
                pesanan.tambahLayanan(new CleanBuild("RG"));
                pesanan.tambahLayanan(new PanelLine(30000));
                pesanan.tambahLayanan(new TopCoat(35000));
                pesanan.tambahLayanan(new Decal(20000));
                session.persist(pesanan);
                ditambah++;
            }

            transaction.commit();

            System.out.println(
                    "Selesai. Pesanan contoh baru yang ditambahkan: " + ditambah);

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Gagal mengisi data contoh!");
            e.printStackTrace();

        } finally {
            HibernateUtil.shutdown();
        }
    }

    private static boolean sudahAda(Session session, String nomorPesanan) {
        Long jumlah = session.createQuery(
                "SELECT COUNT(p) FROM Pesanan p WHERE p.nomorPesanan = :nomor",
                Long.class
        )
        .setParameter("nomor", nomorPesanan)
        .getSingleResult();

        return jumlah > 0;
    }
}
