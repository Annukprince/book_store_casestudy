package org.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.domain.Order;

public record OrderDto(
        Long OrderId,
        @NotNull Long customerId,
        @NotNull Long bookId,
        @Min(1) int quantity,
        @NotNull @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED") String status

) {

}
