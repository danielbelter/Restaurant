package com.app.restaurantgit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderMeal {

   @Id
   @GeneratedValue
    private Long id;
    private int quantity;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderId")
    private Order order;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mealId")
    private Meal meal;


}
