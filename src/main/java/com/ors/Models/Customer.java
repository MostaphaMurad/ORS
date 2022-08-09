package com.ors.Models;

import com.ors.Embedded.Address;
import com.ors.Embedded.FullName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cutsomers")
public class Customer extends Users {
    @Column(nullable = false)
    @Embedded
    Address address;
    @Column(name = "username",nullable = false)
    @Embedded
    FullName fullName;
    @OneToMany(mappedBy = "customer")
    List<Order>orders=new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "role_id")
    Roles role;

    @OneToMany(mappedBy = "customer")
    List<Reservations>reservations=new ArrayList<>();
   /* public void removeOrder(Order order){
        this.orders.remove(order);
    }*/
    public Address getAddress() {
        return address;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
        this.reservations = reservations;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
