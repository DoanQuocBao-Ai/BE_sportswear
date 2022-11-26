package com.store.sportswear.repository;

import com.store.sportswear.entity.ConfirmedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmedOrderRepository extends JpaRepository<ConfirmedOrder, Integer> {
    ConfirmedOrder findConfirmedOrderByOrderNumber(Long orderNumber);
}
