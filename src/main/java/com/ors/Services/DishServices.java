package com.ors.Services;

import com.ors.Models.Chef;
import com.ors.Models.Dish;
import com.ors.Repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServices {
    @Autowired private DishRepository dishRepository;


    public Dish addNewDishByChef(Dish dish, Chef chef) {
        dish.setChef(chef);
        Dish newDish=dishRepository.save(dish);
        return dish;
    }

    public List<Dish> getAllMenu() {
        return dishRepository.findAll();
    }

    public Dish getDishById(int id) {
        Dish dish=dishRepository.findById(id).get();
        return dish;
    }
}
