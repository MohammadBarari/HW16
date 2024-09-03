package org.example.repository.house.imp;

import jakarta.persistence.EntityManager;
import org.example.domain.House;
import org.example.repository.house.HouseRepository;
import org.example.util.HibernateUtil;

public class HouseRepositoryImp implements HouseRepository {

    @Override
    public void save(House house) {
        EntityManager em = HibernateUtil.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(house);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }
}
