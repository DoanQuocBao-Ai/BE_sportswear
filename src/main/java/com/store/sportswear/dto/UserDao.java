package com.store.sportswear.dto;

import com.store.sportswear.entity.EUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
@Transactional
public class UserDao {
    @Autowired
    private EntityManager entityManager;

    public EUser findUserAccount(String userName) {
        try {
            String sql = "Select e.id,e.email,e.notification_permission,e.password,e.create_date,e.username from `" + EUser.class.getName() + "` e " //
                    + " Where e.username = '"+userName+"'";

            Query query = entityManager.createQuery(sql, EUser.class);
            query.setParameter("name", userName);

            return (EUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
