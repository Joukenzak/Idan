/* package com.mycompany.idan;

import com.mycompany.idan.util.HibernateUtil;
import org.hibernate.Session;

public class HibernateTest {

    public static void main(String[] args) {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            Object hasil = session
                    .createNativeQuery("SELECT 1")
                    .getSingleResult();

            System.out.println("Koneksi Hibernate berhasil!");
            System.out.println("Hasil tes database: " + hasil);

        } catch (Exception e) {

            System.out.println("Koneksi Hibernate gagal!");
            e.printStackTrace();

        } finally {

            HibernateUtil.shutdown();

        }
    }
}*/