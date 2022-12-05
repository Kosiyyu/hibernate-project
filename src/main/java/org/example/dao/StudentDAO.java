package org.example.dao;

import lombok.Data;
import org.example.model.ClassGroup;
import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data

public class StudentDAO implements DAO<Student> {
    private final SessionFactory sessionFactory;
    public StudentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Student get(long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        Student result = (Student) entityManager.createNativeQuery(
                        "select * from student where student.id = " + id
                        , Student.class)
                .getResultList().get(0);
        entityManager.close();
        return result;
    }

    @Override
    public List<Student> getAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Student> resultList = entityManager.createNativeQuery(
                        "select * from student"
                        , Student.class)
                .getResultList();
        entityManager.close();
        return resultList;
    }

    @Override
    public void save(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Student student, String[] params) {
        if(params[0] != null){
            student.setFirstname(params[0]);
        }
        if(params[1] != null){
            student.setFirstname(params[1]);
        }
        if(params[2] != null){
            student.setBirthdayDate(new Timestamp(new Date(2000, Calendar.JANUARY, 25).getTime()));
        }
        if(params[3] != null){
            student.setFirstname(params[3]);
        }
        if(params[4] != null){
            student.setFirstname(params[4]);
        }
        if(params[5] != null){
            student.setFirstname(params[5]);
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void delete(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Student student = session.load(Student.class, id);
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        session.close();
    }
}
