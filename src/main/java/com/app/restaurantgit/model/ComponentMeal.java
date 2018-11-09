package com.app.restaurantgit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ComponentMeal {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "componentId")
    private Component component;
    @ManyToOne
    @JoinColumn(name = "mealId")
    private Meal meal;
    private int quantity;

}
