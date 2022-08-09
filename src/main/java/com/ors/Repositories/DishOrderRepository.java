package com.ors.Repositories;

import com.ors.Models.DishOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishOrderRepository extends JpaRepository<DishOrder,Integer> {
}
