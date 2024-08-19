package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Book-Service",url = "http://localhost:8100")
public interface BookClient {
    @GetMapping("/api/v1/books/getquantity/{id}")
    int getQuantity(@PathVariable long id);
}
