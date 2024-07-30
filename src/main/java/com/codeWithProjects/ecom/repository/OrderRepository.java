package com.codeWithProjects.ecom.repository;

import com.codeWithProjects.ecom.entity.Order;
import com.codeWithProjects.ecom.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order> findAllByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatusList);
}
