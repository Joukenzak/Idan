package com.mycompany.idan.service;

import com.mycompany.idan.model.CleanBuild;
import com.mycompany.idan.model.Decal;
import com.mycompany.idan.model.PanelLine;
import com.mycompany.idan.model.Pelanggan;
import com.mycompany.idan.model.Pesanan;
import com.mycompany.idan.model.TopCoat;

public class PemesananService {

    public Pesanan buatContohPesanan() {
        Pelanggan pelanggan = new Pelanggan(
            "Wildan",
            "wildan@example.com",
            "081234567890"
        );

        Pesanan pesanan = new Pesanan(
            "IM-001",
            pelanggan,
            "RX-78-2 Gundam"
        );

        pesanan.tambahLayanan(new CleanBuild("HG"));
        pesanan.tambahLayanan(new PanelLine(20000));
        pesanan.tambahLayanan(new TopCoat(25000));
        pesanan.tambahLayanan(new Decal(15000));

        return pesanan;
    }
}