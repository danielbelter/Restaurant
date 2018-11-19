package com.app.restaurantgit.repository;

import com.app.restaurantgit.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
