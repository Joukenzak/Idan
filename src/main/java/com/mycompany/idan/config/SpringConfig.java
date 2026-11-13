package com.mycompany.idan.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Konfigurasi Spring untuk lapisan DAO.
 *
 * Hibernate dan JDBC memakai SATU DataSource dan SATU transaction manager,
 * sehingga DAO Hibernate dan DAO JdbcTemplate bisa berada dalam transaksi yang sama.
 *
 * Catatan: pengaturan koneksi di bawah sama dengan hibernate.cfg.xml
 * (localhost:3306/Idan, user root, tanpa password). Jika salah satu diubah,
 * ubah juga yang lain.
 */
@Configuration
@ComponentScan(basePackages = "com.mycompany.idan.dao")
@EnableTransactionManagement
public class SpringConfig {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Idan";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Bean
    public DataSource dataSource() {
        // Cukup untuk aplikasi desktop/tugas. Untuk produksi, ganti dengan connection pool.
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(DB_URL);
        ds.setUsername(DB_USER);
        ds.setPassword(DB_PASSWORD);
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource);
        // Semua @Entity di package model (Akun, Pelanggan, Pesanan, Layanan, dst.)
        factory.setPackagesToScan("com.mycompany.idan.model");

        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        factory.setHibernateProperties(props);
        return factory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
