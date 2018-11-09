package com.app.restaurantgit.dao;

import com.app.restaurantgit.model.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComponentMeal extends CrudRepository<Component, Long> {
}
