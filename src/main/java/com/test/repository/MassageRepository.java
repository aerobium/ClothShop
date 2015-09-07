package com.test.repository;

import com.test.config.HibernetUtil;
import com.test.entity.Massage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by RAYANT on 22.05.2015.
 */

@Repository
@Transactional
public class MassageRepository {

    SessionFactory sessionFactory = HibernetUtil.getSessionFactory();


    public void addMassage(Massage massage) throws Exception {
        Session session=null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(massage);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("EXEPTION IN QUERY method = User.addContact");
            e.printStackTrace();
        }

        finally {
            if (session!=null&& session.isOpen()) session.close();
        }

    }

    public Massage getMassage(long id) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            List<Massage> massagesList = new ArrayList<Massage>();
            Query query = session.createQuery("from Massage u where u.id = :id");
            query.setParameter("id", id);
            massagesList = query.list();
            return massagesList.get(0);
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Massage.getMassagesPostById");
            e.printStackTrace();
        }
        finally
        {
            if(session !=null && session.isOpen())
            {
                session.close();

            }
        }
        return null;
    }


    public List<Massage> getMassagesPlased (long place){
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createQuery("from Massage u where u.place = :place");
            query.setParameter("place", place);
            return query.list();
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = User.getUserByEmail");
            e.printStackTrace();
        }
        finally
        {
            if(session !=null && session.isOpen())
            {
                session.close();

            }
        }
        return null;


    }




    public List<Massage> getMassagesPostById(long authorId) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            List<Massage> massagesList = new ArrayList<Massage>();
            Query query = session.createQuery("from Massage u where u.authorId = :authorId");
            query.setParameter("authorId", authorId);
            return query.list();
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Massage.getMassagesPostById");
            e.printStackTrace();
        }
        finally
        {
            if(session !=null && session.isOpen())
            {
                session.close();

            }
        }
        return null;
    }



    public List<Massage> getMassagesPost(String author) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            List<Massage> massagesList = new ArrayList<Massage>();
            Query query = session.createQuery("from Massage u where u.author = :author");
            query.setParameter("author", author);
            return query.list();
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Massage.getMassagesPost");
            e.printStackTrace();
        }
        finally
        {
            if(session !=null && session.isOpen())
            {
                session.close();

            }
        }
        return null;
    }

    public List<Massage> getMassagesReciveById(long recipientId) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createQuery("from Massage u where u.recipientId = :recipientId");
            query.setParameter("recipientId", recipientId);
            return query.list();
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Massage.getMassage");
            e.printStackTrace();
        }
        finally
        {
            if(session !=null && session.isOpen())
            {
                session.close();

            }
        }
        return null;
    }

    public List<Massage> getMassagesRecive(String recipient) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createQuery("from Massage u where u.recipient = :recipient");
            query.setParameter("recipient", recipient);
            return query.list();
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Massage.getMassagesRecive");
            e.printStackTrace();
        }
        finally
        {
            if(session !=null && session.isOpen())
            {
                session.close();

            }
        }
        return null;
    }

    public List<Massage> listAll() throws Exception {

        Session session=null;
        try
        {
            session = sessionFactory.openSession();
//            return session.createSQLQuery("SELECT * FROM users").list();
            return session.createQuery("from Massage").list();
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Massage.listAll()");
        }
        finally
        {
            if(session !=null && session.isOpen())
            {
                session.close();

            }
        }
        return new ArrayList<Massage>();
    }

    public MassageRepository() {
    }

    public void removeMassage(Massage massage) throws Exception {
        Session session=null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(massage);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("EXEPTION IN QUERY method = Massage.removeMassage");
            e.printStackTrace();
        }

        finally {
            if (session!=null&& session.isOpen()) session.close();
        }

    }

    public void removeMassages(Set<Massage> massageSet) throws Exception {
        Session session=null;
        if (massageSet!=null) {
            for (Massage massage : massageSet) {
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.delete(massage);
                    session.getTransaction().commit();
                } catch (Exception e) {
                    System.out.println("EXEPTION IN QUERY method = Massage.removeMassages");
                    e.printStackTrace();
                } finally {
                    if (session != null && session.isOpen()) session.close();
                }
            }

        }

    }

    public void updateMassages(Collection<Massage> massageSet) throws Exception {
        Session session=null;
        if (massageSet!=null) {
            for (Massage massage : massageSet) {
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.update(massage);
                    session.getTransaction().commit();
                } catch (Exception e) {
                    System.out.println("EXEPTION IN QUERY method = Massage.removeMassages");
                    e.printStackTrace();
                } finally {
                    if (session != null && session.isOpen()) session.close();
                }
            }

        }

    }


    public void updateMassage (Massage massage){
        Session session=null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(massage);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("EXEPTION IN QUERY method = Massage.updateMassage");
            e.printStackTrace();
        }

        finally {
            if (session!=null&& session.isOpen()) session.close();
        }

    }

}
