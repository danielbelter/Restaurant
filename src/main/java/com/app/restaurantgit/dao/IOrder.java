package com.app.restaurantgit.dao;

import com.app.restaurantgit.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrder extends CrudRepository<Order, Long> {
}
