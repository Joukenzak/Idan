/*
package com.mycompany.idan;

import com.mycompany.idan.model.Pesanan;
import com.mycompany.idan.service.PemesananService;
import com.mycompany.idan.util.HibernateUtil;

public class HibernateSaveTest {

    public static void main(String[] args) {

        try {
            PemesananService service = new PemesananService();

            Pesanan pesanan = service.buatContohPesanan();

            if (pesanan != null) {
                System.out.println("\nData berhasil disimpan!");
                pesanan.tampilkanStruk();
            }

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan:");
            e.printStackTrace();

        } finally {
            HibernateUtil.shutdown();
        }
    }
}
*/