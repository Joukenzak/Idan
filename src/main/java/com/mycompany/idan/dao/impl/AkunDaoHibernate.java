package com.mycompany.idan.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.idan.dao.AkunDao;
import com.mycompany.idan.model.Akun;

@Repository("akunDaoHibernate")
@Transactional
public class AkunDaoHibernate implements AkunDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void simpan(Akun akun) {
        em.persist(akun);
    }

    @Override
    @Transactional(readOnly = true)
    public Akun cariById(Long id) {
        return em.find(Akun.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Akun cariByUsername(String username) {
        List<Akun> hasil = em.createQuery(
                "SELECT a FROM Akun a WHERE a.username = :username", Akun.class)
                .setParameter("username", username)
                .getResultList();
        return hasil.isEmpty() ? null : hasil.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Akun> cariSemua() {
        return em.createQuery(
                "SELECT a FROM Akun a ORDER BY a.id", Akun.class)
                .getResultList();
    }

    @Override
    public void perbarui(Akun akun) {
        em.merge(akun);
    }

    @Override
    public void hapus(Long id) {
        Akun akun = em.find(Akun.class, id);
        if (akun == null) {
            throw new IllegalArgumentException("Akun tidak ditemukan.");
        }
        em.remove(akun);
    }
}