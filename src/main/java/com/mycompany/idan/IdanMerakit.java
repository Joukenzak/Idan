package com.mycompany.idan;

import com.mycompany.idan.model.Pesanan;
import com.mycompany.idan.service.PemesananService;

public class IdanMerakit {

    public static void tampilkanContohPesanan() {
        PemesananService pemesananService =
                new PemesananService();

        Pesanan pesanan =
                pemesananService.buatContohPesanan();

        pesanan.tampilkanStruk();
    }
}