package com.codeWithProjects.ecom.repository;

import com.codeWithProjects.ecom.entity.Order;
import com.codeWithProjects.ecom.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
}
