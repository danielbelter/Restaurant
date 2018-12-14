package com.app.restaurantgit.repository;

import com.app.restaurantgit.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository <Meal,Long> {
    // List<Meal> findByCategory(String category);
    List<Meal> findByCategory_Name(String category);
    Meal findByName(String name);
}
