package com.example.demo.repo;

import com.example.demo.dto.UserDataDto;
import com.example.demo.modal.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.time.Instant;

@Repository
@Transactional
public class UserRepoImpl implements UserRepo{

    @Autowired
    SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(UserRepoImpl.class);
    @Override
    public boolean saveOrUpdate(User user) {
        boolean success = false;
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error("Exception in saveOrUpdate() {} : {}", user.getClass().getSimpleName(), e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return success;
    }

    @Override
    public boolean deleteUser(UserDataDto dto) {
        Session session=null;
        Transaction tx=null;
        boolean success=false;
        try {
            session=sessionFactory.openSession();
            tx=session.beginTransaction();
            Query query= session.createQuery("delete User where userName=:username and password=:password");
            query.setParameter("username",dto.getUserName());
            query.setParameter("password",dto.getPassword());
            int rowAffected=query.executeUpdate();
            if(rowAffected>0){
                success=true;
            }
        }
        catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            logger.error("Exception in deleting user()", e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return success;
    }

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        Session session = null;
        Transaction tx = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE userName = :username AND password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            user = (User) query.getSingleResult();
            tx.commit();
        } catch (NoResultException e) {
            // Handle case where no result is found
            user = null;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.error("Exception in findByUserNameAndPassword()", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

}