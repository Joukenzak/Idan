package com.mycompany.idan;

import com.mycompany.idan.model.Akun;
import com.mycompany.idan.service.AkunService;
import com.mycompany.idan.util.HibernateUtil;

public class HibernateAkunTest {

    public static void main(String[] args) {
        try {
            AkunService service = new AkunService();

            // Mencari akun yang akan dihapus
            Akun akun = service.cariAkunByUsername(
                "akun_hibernate_update"
            );

            if (akun != null) {
                // Menghapus akun berdasarkan ID
                service.hapusAkun(akun.getId());
            } else {
                System.out.println("Akun pengujian tidak ditemukan.");
            }

            // Menampilkan seluruh akun setelah penghapusan
            service.tampilkanSemuaAkun();

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan:");
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
