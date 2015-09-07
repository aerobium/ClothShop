package com.test.repository;

/**
 * Created by Optical Illusion on 28.04.2015.
 */

import com.test.config.HibernetUtil;
import com.test.entity.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ResourceRepository {

    SessionFactory sessionFactory = HibernetUtil.getSessionFactory();

    public void addContact(Resource picture) throws Exception {
        Session session=null;
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(picture);
        session.getTransaction().commit();
        session.close();
    }



    public List<Resource> listAll() throws Exception {

        Session session=null;
        try
        {
            session = sessionFactory.openSession();
//            return session.createSQLQuery("SELECT * FROM users").list();
            return session.createQuery("from Resource").list();
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Pictures.listAll()");
            e.printStackTrace();
        }
        finally
        {
            if(session !=null && session.isOpen())
            {
                session.close();

            }
        }
        return new ArrayList<Resource>();
    }

    public List<String> getAllUrl () throws Exception{
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
//            return session.createSQLQuery("SELECT * FROM users").list();
            return session.createQuery("SELECT p.imgurl FROM Resource p").list();
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Pictures.getAllUrl()");
            e.printStackTrace();
        }
        finally
        {
            if(session !=null && session.isOpen())
            {
                session.close();

            }
        }
        return new ArrayList<String>();
    }



    public ResourceRepository() {
    }

    public void updateResource (Resource resource){
        Session session=null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(resource);
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

    public Resource getResource(long id) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            List<Resource> resourcesList = new ArrayList<Resource>();
            Query query = session.createQuery("from Resource u where u.id = :id");
            query.setParameter("id", id);
            resourcesList = query.list();
            return resourcesList.get(0);
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = Resource.getResource id = " +id);
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

    public void deleteResource (Resource resource) {
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
