package com.app.restaurantgit.repository;

import com.app.restaurantgit.model.Meal;
import com.app.restaurantgit.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    // List<Order> findAllByOrderStatusIsNotContaining;
    List<Order> findAllByOrOrderStatus_ZrealizowaneIsNotContaining();
}
