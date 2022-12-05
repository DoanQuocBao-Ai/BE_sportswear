package com.store.sportswear.dto;

import com.store.sportswear.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class RoleDao {
    @Autowired
    private EntityManager entityManager;

    public List<String> getRoleNames(int userId) {
        String sql = "Select ur.id,ur.role_id,ur.user_id from " + UserRole.class.getName() + " ur " //
                + " where ur.user_id = '"+userId+"'";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
