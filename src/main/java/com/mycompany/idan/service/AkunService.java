package com.mycompany.idan.service;

import com.mycompany.idan.model.Akun;
import com.mycompany.idan.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AkunService {

    // CREATE: Menambahkan akun baru
    public void tambahAkun(Akun akun) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            // Memeriksa username yang sudah terdaftar
            Long jumlah = session.createQuery(
                    "SELECT COUNT(a) FROM Akun a "
                    + "WHERE a.username = :username",
                    Long.class
            )
            .setParameter("username", akun.getUsername())
            .getSingleResult();

            if (jumlah > 0) {
                throw new IllegalArgumentException(
                        "Username sudah digunakan."
                );
            }

            session.persist(akun);

            transaction.commit();

            System.out.println("Akun berhasil ditambahkan!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Gagal menambahkan akun!");
            e.printStackTrace();
        }
    }

    // READ: Mencari akun berdasarkan username
    public Akun cariAkunByUsername(String username) {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            return session.createQuery(
                    "FROM Akun a WHERE a.username = :username",
                    Akun.class
            )
            .setParameter("username", username)
            .uniqueResult();

        } catch (Exception e) {

            System.out.println("Gagal mencari akun!");
            e.printStackTrace();

            return null;
        }
    }

    // READ: Menampilkan seluruh akun
    public void tampilkanSemuaAkun() {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            List<Akun> daftarAkun = session.createQuery(
                    "FROM Akun a ORDER BY a.id",
                    Akun.class
            ).getResultList();

            if (daftarAkun.isEmpty()) {
                System.out.println("Belum ada akun.");
                return;
            }

            System.out.println("\n================================");
            System.out.println("       DAFTAR SELURUH AKUN");
            System.out.println("================================");

            for (Akun akun : daftarAkun) {
                System.out.println("ID       : " + akun.getId());
                System.out.println("Username : " + akun.getUsername());
                System.out.println("--------------------------------");
            }

            System.out.println("Jumlah akun: " + daftarAkun.size());

        } catch (Exception e) {

            System.out.println("Gagal mengambil data akun!");
            e.printStackTrace();
        }
    }

    // UPDATE: Mengubah username dan password
    public void ubahAkun(
            Long id,
            String usernameBaru,
            String passwordBaru) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            Akun akun = session.get(Akun.class, id);

            if (akun == null) {
                throw new IllegalArgumentException(
                        "Akun tidak ditemukan."
                );
            }

            akun.setUsername(usernameBaru);
            akun.setPassword(passwordBaru);

            transaction.commit();

            System.out.println("Akun berhasil diperbarui!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Gagal memperbarui akun!");
            e.printStackTrace();
        }
    }

    // DELETE: Menghapus akun berdasarkan ID
    public void hapusAkun(Long id) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaction = session.beginTransaction();

            Akun akun = session.get(Akun.class, id);

            if (akun == null) {
                throw new IllegalArgumentException(
                        "Akun tidak ditemukan."
                );
            }

            session.remove(akun);

            transaction.commit();

            System.out.println("Akun berhasil dihapus!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Gagal menghapus akun!");
            e.printStackTrace();
        }
    }
}
