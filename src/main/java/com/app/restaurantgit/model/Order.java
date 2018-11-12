package com.app.restaurantgit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customerId")
    private Customer customer;
    private Integer priority;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "mealOrder",
            joinColumns = {@JoinColumn(name = "orderId")},
            inverseJoinColumns = {@JoinColumn(name = "mealId")}
    )
    private List<Meal> meals = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priority);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", priority=" + priority +
                '}';
    }
}
