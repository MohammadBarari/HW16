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
        try {


        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        Query query = entityManager.createNativeQuery("""
        select * from student where studentnumber = ?
""",Student.class);
        query.setParameter(1, studentNumber);
        Student student = (Student) query.getSingleResult();
        return student;}catch (Exception e){
            return null;
        }
    }

    @Override
    public Student findByUserAndPass(String user, String pass) {
        try {
            EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();

            Query query = entityManager.createNativeQuery("""
        select * from student where username = ? and
                                                password = ?;
""", Student.class);
            query.setParameter(1, user);
            query.setParameter(2, pass);
            Student student = (Student) query.getSingleResult();
            return student;
        }catch (Exception e){
            //Query("from Student where user = :user and password = :pass");
            return null;
        }
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
        try {


        EntityManager entityManager = HibernateUtil.getInstance().getEntityManager();
        Query query = entityManager.createNativeQuery("""
        select s from student s where s.nationalcode = ?
""");
        query.setParameter(1, nationalCode);
        Student student = (Student) query.getSingleResult();
        return student;}catch (Exception e){
            return null;
        }
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
