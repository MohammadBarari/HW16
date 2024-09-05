package org.example.repository.loan.base.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.domain.Bill;
import org.example.domain.Loan;
import org.example.domain.Student;
import org.example.dto.LoanDto;
import org.example.repository.loan.base.BaseLoanRepository;
import org.example.service.loan.base.BaseLoan;
import org.example.util.HibernateUtil;

import java.util.List;

public class BaseLoanRepositoryImp implements BaseLoanRepository {

    @Override
    public List<Loan> selectAllLoan(Student student) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();

        Query query = entityManager.createNativeQuery(
                "select l.* from loan l join student s on s.id = l.student_id where l.student_id = ?"
        ,Loan.class);
        query.setParameter(1, student.getId());
        List<Loan> loans = query.getResultList();
        return loans;
    }

    @Override
    public List<Bill> allNonPaidBillForALoan(Integer loanId) {
        try {
            EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
            Query query = entityManager.createNativeQuery("" +
                    "select * from bill b where b.loan_id = ? and b.ispaid = false", Bill.class);
            query.setParameter(1, loanId);
            List<Bill> bills = query.getResultList();
            return bills;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Bill> allPaidBillForALoan(Integer loanId) {
        try {
            EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
            Query query = entityManager.createNativeQuery("" +
                    "select * from bill b where b.loan_id = ? and b.ispaid = true", Bill.class);
            query.setParameter(1, loanId);
            List<Bill> bills = query.getResultList();
            return bills;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void saveLoan(Loan loan) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        try {
        entityManager.getTransaction().begin();
        entityManager.persist(loan);
        entityManager.getTransaction().commit();
    }catch (Exception e) {
        entityManager.getTransaction().rollback();
        }
    }
}
