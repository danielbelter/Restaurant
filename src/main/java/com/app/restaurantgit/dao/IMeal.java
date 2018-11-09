package com.app.restaurantgit.dao;

import com.app.restaurantgit.model.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMeal extends CrudRepository<Meal, Long> {
}
