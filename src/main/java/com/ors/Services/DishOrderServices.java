package com.ors.Services;

import com.ors.Models.DishOrder;
import com.ors.Repositories.DishOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishOrderServices {
    @Autowired private DishOrderRepository dishOrderRepository;

    public boolean addDishOrder(DishOrder dishOrder1) {
        DishOrder dishOrder=dishOrderRepository.save(dishOrder1);
        if(dishOrder!=null)return true;
        return false;
    }

    public List<DishOrder> getAllDishOrders() {
        return dishOrderRepository.findAll();
    }
}
