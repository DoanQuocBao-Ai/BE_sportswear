package com.store.sportswear.repository;

import com.store.sportswear.entity.EUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<EUser, Integer> {
    EUser getById(int id);
    EUser findByUserName(String userName);

    @Query("from EUser where eMail=:eMail")
    EUser findByEMail(String eMail);
}
