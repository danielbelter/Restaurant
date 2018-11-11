package com.app.restaurantgit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @ManyToOne()
    @JoinColumn(name = "mealId")
    private Meal meal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return quantity == component.quantity &&
                Objects.equals(id, component.id) &&
                Objects.equals(name, component.name) &&
                Objects.equals(price, component.price) &&
                Objects.equals(meal, component.meal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity, meal);
    }

    @Override
    public String toString() {
        return "Components: " + name;

    }
}
