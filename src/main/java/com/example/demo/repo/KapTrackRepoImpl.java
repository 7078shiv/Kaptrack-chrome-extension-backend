package com.example.demo.repo;

import com.example.demo.modal.KapTrackModal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class KapTrackRepoImpl implements KapTrackRepo {

    @Autowired
    SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(KapTrackRepoImpl.class);
    @Override
    public boolean saveOrUpdate(KapTrackModal modal) {
            boolean success = false;
            Session session = null;
            Transaction tx = null;
            try {
                session = sessionFactory.openSession();
                tx = session.beginTransaction();

                Query query = session.createQuery("FROM KapTrackModal WHERE url = :url AND date = :date");
                query.setParameter("url", modal.getUrl());
                query.setParameter("date", modal.getDate());
                KapTrackModal existingModal=null;
                try {
                    existingModal = (KapTrackModal) query.getSingleResult();
                } catch (NoResultException e) {
                    // No existing record found
                }

                if (existingModal == null) {
                    session.save(modal);
                } else {
                    existingModal.setTimeTaken(modal.getTimeTaken());
                    existingModal.setDate(modal.getDate());
                    session.update(existingModal);
                }

                tx.commit();
                success = true;
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                logger.error("Exception in saveOrUpdate() {} : {}", modal.getClass().getSimpleName(), e);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            return success;
        }
}
