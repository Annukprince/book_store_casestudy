package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.domain.Customer;

public record CustomerDto(
        Long id,
        @NotBlank String name,
        @NotBlank @Email String email,
        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$") String phoneNumber
) {

    public static CustomerDto fromCustomer(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }

    public Customer toCustomer() {
        return new Customer(
                id,
                name,
                email,
                phoneNumber
        );
    }
}
