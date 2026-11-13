package com.mycompany.idan.dao;

import java.util.List;

/**
 * DAO akun berbasis JDBC murni lewat Spring JdbcTemplate.
 * Bekerja pada tabel "login" yang sama dengan entity Akun.
 */
public interface AkunJdbcDao {

    /** Data ringkas akun (tanpa password). */
    record AkunRingkas(Long id, String username) {
    }

    int hitungSemua();

    boolean usernameSudahAda(String username);

    List<AkunRingkas> daftarRingkas();

    int ubahPassword(Long id, String passwordBaru);
}
