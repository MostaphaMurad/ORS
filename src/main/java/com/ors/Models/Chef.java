package com.ors.Models;

import com.ors.Embedded.FullName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Chef extends Employee{
    @OneToMany(mappedBy = "chef")
    List<Dish>dishes=new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "salary_id")
    private Salary salary;
    @Column(name = "chef_name",nullable = false,length = 20)
    private String userName;
    @OneToOne
    @JoinColumn(name = "role_id")
    Roles role;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }
}
