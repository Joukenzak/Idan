package com.mycompany.idan.dao;

import java.util.List;

import com.mycompany.idan.model.Akun;

/**
 * DAO akun berbasis Hibernate (dikelola Spring).
 */
public interface AkunDao {

    void simpan(Akun akun);

    Akun cariById(Long id);

    Akun cariByUsername(String username);

    List<Akun> cariSemua();

    void perbarui(Akun akun);

    void hapus(Long id);
}
