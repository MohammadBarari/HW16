package org.example.repository.bill.imp;

import jakarta.persistence.EntityManager;
import org.example.domain.Bill;
import org.example.repository.bill.BillRepository;
import org.example.util.HibernateUtil;

public class BillRepositoryImp implements BillRepository {
    @Override
    public Bill findById(Integer id) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        return entityManager.find(Bill.class, id);
    }

    @Override
    public Bill update(Bill bill) {
        EntityManager entityManager = 
    }
}
