package org.example.service;

import org.example.domain.Order;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findAnOrder(long id) {

        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order with id:"+id+"not found"));
    }

    @Override
    public Order createAnOrder(Order Order) {
        return orderRepository.save(Order);
    }

    @Override
    public Order updateAnOrder(long id, Order order) throws RuntimeException {
        return orderRepository.findById(order.getOrderId()).map(existingOrder->{
            existingOrder.setCustomerId(order.getCustomerId());
            existingOrder.setBookId(order.getBookId());
            existingOrder.setQuantity(order.getQuantity());
            existingOrder.setStatus(order.getStatus());
            return orderRepository.save(existingOrder);
        }).orElseThrow(()->new RuntimeException("Order does not exist"));

    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);

    }
}
