package org.example.repository.student.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.domain.Person;
import org.example.domain.Student;
import org.example.repository.student.StudentRepository;
import org.example.util.HibernateUtil;

public class StudentRepositoryImp implements StudentRepository {
    @Override
    public void save(Student student) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public Student findByStudentNumber(String studentNumber) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        Query query = entityManager.createNativeQuery("""
        select * from hw16_schema.student where studentnumber = ?
""");
        query.setParameter(1, studentNumber);
        Student student = (Student) query.getSingleResult();
        return student;
    }

    @Override
    public Student findByUserAndPass(String user, String pass) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        Query query = entityManager.createNativeQuery("""
        select * from hw16_schema.student where username = ? and
                                                password = ?;
""");
        query.setParameter(1, user);
        query.setParameter(2, pass);
        Student student = (Student) query.getSingleResult();
        return student;
        //Query("from Student where user = :user and password = :pass");
    }

    @Override
    public void studentUpdate(Student student) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(student);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public Student findStudentByNationalCode(String nationalCode) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        Query query = entityManager.createNativeQuery("""
        select s from hw16_schema.student s, hw16_schema.person p where s.id = p.id\s
        and p.nationalcode = ?
""");
        query.setParameter(1, nationalCode);
        Student student = (Student) query.getSingleResult();
        return student;
    }

    @Override
    public void savePerson(Person person) {
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
}
