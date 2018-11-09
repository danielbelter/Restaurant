package com.app.restaurantgit.dao;

import com.app.restaurantgit.model.OrderMeal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderMeal extends CrudRepository<OrderMeal, Long> {
}
