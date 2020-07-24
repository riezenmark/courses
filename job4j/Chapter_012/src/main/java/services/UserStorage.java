package services;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;

public class UserStorage {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setLogin("created");
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setId(1);
        session.delete(user);
        System.out.println(session.createQuery("from User").list());
        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
