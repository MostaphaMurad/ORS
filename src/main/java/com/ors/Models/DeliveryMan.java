package com.ors.Models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class DeliveryMan  extends Employee{
@OneToOne
@JoinColumn(name = "order_id")
Order order;
@OneToOne
@JoinColumn(name = "salary_id")
private Salary salary;
@OneToOne
@JoinColumn(name = "role_id")
Roles role;

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
