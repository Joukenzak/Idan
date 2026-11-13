package com.mycompany.idan.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.idan.dao.AkunJdbcDao;

@Repository("akunDaoJdbc")
@Transactional
public class AkunJdbcDaoImpl implements AkunJdbcDao {

    private final JdbcTemplate jdbcTemplate;

    public AkunJdbcDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(readOnly = true)
    public int hitungSemua() {
        Integer jumlah = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM login", Integer.class);
        return jumlah == null ? 0 : jumlah;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean usernameSudahAda(String username) {
        Integer jumlah = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM login WHERE username = ?",
                Integer.class, username);
        return jumlah != null && jumlah > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AkunRingkas> daftarRingkas() {
        return jdbcTemplate.query(
                "SELECT id, username FROM login ORDER BY id",
                (rs, rowNum) -> new AkunRingkas(
                        rs.getLong("id"), rs.getString("username")));
    }

    @Override
    public int ubahPassword(Long id, String passwordBaru) {
        if (passwordBaru == null || passwordBaru.isBlank()) {
            throw new IllegalArgumentException("Password tidak boleh kosong.");
        }
        return jdbcTemplate.update(
                "UPDATE login SET password = ? WHERE id = ?",
                passwordBaru, id);
    }
}
