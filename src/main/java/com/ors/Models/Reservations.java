package com.ors.Models;
import com.ors.Embedded.TimeSlot;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer_id")
    Customer customer;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    @JoinColumn(name = "table_id")
    private RestuarantTables restuarantTable;
    @Embedded
    private TimeSlot timeSlot;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public RestuarantTables getRestuarantTable() {
        return restuarantTable;
    }

    public void setRestuarantTable(RestuarantTables restuarantTable) {
        this.restuarantTable = restuarantTable;
    }
}
