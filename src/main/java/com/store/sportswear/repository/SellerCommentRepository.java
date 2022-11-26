package com.store.sportswear.repository;

import com.store.sportswear.entity.SellerComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerCommentRepository extends JpaRepository<SellerComment, Integer> {
}
