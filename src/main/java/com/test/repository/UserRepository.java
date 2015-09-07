package com.test.repository;

import com.test.config.HibernetUtil;
import com.test.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserRepository {


    SessionFactory sessionFactory = HibernetUtil.getSessionFactory();
    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addContact(User user) throws Exception {
        Session session=null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
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


    public User getUserByEmail (String email){
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            List<User> userList = new ArrayList<User>();
            Query query = session.createQuery("from User u where u.email = :email");
            query.setParameter("email", email);
            userList = query.list();
            return userList.get(0);
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


    public User getUser(String login) {
        if (login.equals("anonymousUser")||login.isEmpty()) return null;
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            List<User> userList = new ArrayList<User>();
            Query query = session.createQuery("from User u where u.login = :login");
            query.setParameter("login", login);
            userList = query.list();
            return userList.get(0);
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = User.getUser login = " +login);
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


    public List<User> listAll() throws Exception {

        Session session=null;
        try
        {
            session = sessionFactory.openSession();
//            return session.createSQLQuery("SELECT * FROM users").list();
            return session.createQuery("from User").list();
        }
        catch(Exception e)
        {
            System.out.println("EXEPTION IN QUERY method = User.listAll()");
        }
        finally
        {
            if(session !=null && session.isOpen())
            {
                session.close();

            }
        }
        return new ArrayList<User>();
    }

    public UserRepository() {
    }

    public void removeUser(int iD) throws Exception {
        User contact = (User) HibernetUtil.getSessionFactory().getCurrentSession().load(User.class, iD);
        if (null!=contact){
            HibernetUtil.getSessionFactory().getCurrentSession().delete(contact);
        }
    }

    public void updateUser (User user){
        Session session=null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("EXEPTION IN QUERY method = User.updateUser");
            e.printStackTrace();
        }

        finally {
            if (session!=null&& session.isOpen()) session.close();
        }



    }
}

