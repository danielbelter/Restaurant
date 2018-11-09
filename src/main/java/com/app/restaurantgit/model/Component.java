package com.app.restaurantgit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Component {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "component")
    private List<ComponentMeal> componentMeals = new ArrayList<>();
}
