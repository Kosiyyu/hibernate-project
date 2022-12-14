package org.example.dao;

import org.example.model.Classes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO implements DAO<Classes>{

    private final SessionFactory sessionFactory;

    public ClassesDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Classes get(long id) {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            Classes result = (Classes) entityManager.createNativeQuery(
                            "select * from classes where classes.id = " + id
                            , Classes.class)
                    .getResultList().get(0);
            entityManager.close();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Classes> getAll() {
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            List<Classes> resultList = entityManager.createNativeQuery(
                            "select * from classes"
                            , Classes.class)
                    .getResultList();
            entityManager.close();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Classes>();
    }

    @Override
    public void save(Classes classes) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(classes);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public void update(Classes classes, Classes classesUpdate) {

    }

    @Override
    public void delete(Classes classes) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(classes);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        try {
            Classes classes = session.load(Classes.class, id);
            session.beginTransaction();
            session.delete(classes);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }
}
