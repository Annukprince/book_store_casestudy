package org.example.service;

import org.example.client.BookClient;
import org.example.domain.Order;
import org.example.dto.OrderDto;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Correct import

import java.util.List;

@Service
public interface OrderService {
        List<Order> findAllOrders();
        Order findAnOrder(long id);
        Order createAnOrder(Order Order);
        Order updateAnOrder(long id, Order Order);
        void deleteOrder(long id);
    }

