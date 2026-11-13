package com.mycompany.idan.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.idan.dao.AkunDao;
import com.mycompany.idan.model.Akun;

@Repository("akunDaoHibernate")
@Transactional
public class AkunDaoHibernate implements AkunDao {

    private final SessionFactory sessionFactory;

    public AkunDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void simpan(Akun akun) {
        session().persist(akun);
    }

    @Override
    @Transactional(readOnly = true)
    public Akun cariById(Long id) {
        return session().get(Akun.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Akun cariByUsername(String username) {
        return session().createQuery(
                "FROM Akun a WHERE a.username = :username", Akun.class)
                .setParameter("username", username)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Akun> cariSemua() {
        return session().createQuery(
                "FROM Akun a ORDER BY a.id", Akun.class)
                .getResultList();
    }

    @Override
    public void perbarui(Akun akun) {
        session().merge(akun);
    }

    @Override
    public void hapus(Long id) {
        Akun akun = session().get(Akun.class, id);
        if (akun == null) {
            throw new IllegalArgumentException("Akun tidak ditemukan.");
        }
        session().remove(akun);
    }
}
