package org.example.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory emf;
    public static HibernateUtil getInstance() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("jpa");
        }
        return hibernateUtil;
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        entityManager.getTransaction().begin();
    }
}
