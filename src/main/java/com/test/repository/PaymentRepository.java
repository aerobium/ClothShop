package com.test.repository;

import com.test.config.HibernetUtil;
import com.test.entity.Payment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAYANT on 06.07.2015.
 */

@Repository
@Transactional
public class PaymentRepository {

    SessionFactory sessionFactory = HibernetUtil.getSessionFactory();

    public void add(Payment transaction) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(transaction);
        session.getTransaction().commit();
        session.close();
    }

    public List<Payment> listAll() throws Exception {

        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            return session.createQuery("from Payment").list();
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Payment.listAll()");
            e.printStackTrace();
        }
        finally
        {
            if(session !=null && session.isOpen()) session.close();
        }
        return new ArrayList<Payment>();
    }

    public PaymentRepository() {
    }

    public void update (Payment transaction){
        Session session=null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(transaction);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("EXEPTION IN QUERY method = Resource.updateResource");
            e.printStackTrace();
        }

        finally {
            if (session!=null&& session.isOpen()) session.close();
        }

    }

    public List<Payment> get(String login) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            List<Payment> resourcesList = new ArrayList<Payment>();
            Query query = session.createQuery("from Payment u where u.login = :login");
            query.setParameter("login", login);
            resourcesList = query.list();
            return resourcesList;
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Resource.getResource id = " +login);
            e.printStackTrace();
        }
        finally
        {
            if(session !=null && session.isOpen()) session.close();
        }
        return null;
    }

    public void delete (Payment resource) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(resource);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("EXEPTION IN QUERY method = Resource.deleteResource");
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }
}
