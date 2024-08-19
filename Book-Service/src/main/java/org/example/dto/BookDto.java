package org.example.dto;

//import com.netflix.discovery.converters.Auto;
import jakarta.validation.constraints.*;

public record BookDto(

        Long id,

        @NotBlank(message = "name cannot be empty")
        @Size(min = 1,max = 255,message = "name must be between 1 and 255 character")
        String name,

        @NotBlank(message = "Authorname cannot be empty")
        @Size(min=1,max=255,message = "Authors name must be in length between 1 and 255")
        String authorName,

        @NotNull(message = "price cannot be null")
        @Min(value = 0,message = "stock must be zero or greater")
        int stock,

        @NotNull(message = "price cannot be empty")
        @DecimalMin(value = "0.01",inclusive = true,message = "price must be greater than zero")
        float price
) {
}
