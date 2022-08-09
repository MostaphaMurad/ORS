package com.ors.Repositories;

import com.ors.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Integer> {
    @Query("select u from Order u where u.status=0")
    List<Order> findByStatus();
    @Query("select  o from Order o where o.orderId=?1")
    List<Order> findOrdersByCustomerId(int customerId);

    List<Order> findByCustomer_id(int customerId);
}
