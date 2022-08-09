package com.ors.Services;

import com.ors.Models.Order;
import com.ors.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices {
    @Autowired private OrderRepository orderRepository;
    public boolean saveOrder(Order order) {
        Order order1=orderRepository.save(order);
        if(order1!=null)return true;
        return false;
    }

    public List<Order> getReadyOrders() {
        List<Order>orders=orderRepository.findByStatus();
        return orders;
    }

    public Order getOrderByIDd(int orderId) {
        Order order=orderRepository.findById(orderId).get();
        return order;
    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order>orders=orderRepository.findByCustomer_id(customerId);
        return orders;
    }

    public boolean deleteOrderById(int orderId) {
        orderRepository.deleteById(orderId);
        return true;
    }

    public List<Order> getallOrders() {
        return orderRepository.findAll();
    }
}
