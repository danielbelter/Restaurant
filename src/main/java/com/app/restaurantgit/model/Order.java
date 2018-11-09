package com.app.restaurantgit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
    private LocalDate deliverTime;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
    private List<OrderMeal> orderMeals = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(deliverTime, order.deliverTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliverTime);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", deliverTime=" + deliverTime +
                '}';
    }
}
