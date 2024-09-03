package org.example;

import jakarta.persistence.EntityManager;
import org.example.util.HibernateUtil;
import org.junit.jupiter.api.Test;

public class HibernateUtilTest {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
    }
    @Test
    public void test() {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
    }
}
