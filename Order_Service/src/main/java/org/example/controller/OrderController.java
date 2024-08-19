package org.example.controller;

import org.example.client.BookClient;
import org.example.domain.Order;
import org.example.dto.OrderDto;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookClient bookFeignClient;

    @PostMapping
    public ResponseEntity<?> createAOrder(@RequestBody @Valid OrderDto orderDto) {
        try {
            int availableQuantity = bookFeignClient.getQuantity(orderDto.bookId());

            if (orderDto.quantity() <= availableQuantity) {
                Order order = toEntity(orderDto);
                Order createdOrder = orderService.createAnOrder(order);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Specified quantity exceeds available stock.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the order: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAOrder(@PathVariable long id) {
        try {
            Order order = orderService.findAnOrder(id);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAOrder(@PathVariable long id, @RequestBody @Valid OrderDto orderDto) {
        try {
            Order updatedOrder = toEntity(orderDto);
            updatedOrder.setOrderId(id);
            Order order = orderService.updateAnOrder(id, updatedOrder);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAOrder(@PathVariable long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found: " + e.getMessage());
        }
    }

    private Order toEntity(OrderDto orderDto) {
        return new Order(
                orderDto.OrderId(),
                orderDto.customerId(),
                orderDto.bookId(),
                orderDto.quantity(),
                orderDto.status()
        );
    }
}
