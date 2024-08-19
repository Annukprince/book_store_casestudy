package org.example.service;

import org.example.domain.Customer;
import org.example.dto.CustomerDto;
import org.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto createCustomer(CustomerDto customerDto){
        Customer customer=customerDto.toCustomer();
        customer=customerRepository.save(customer);
        return CustomerDto.fromCustomer(customer);
    }

    public CustomerDto getCustomer(Long id){
        return customerRepository.findById(id)
                .map(CustomerDto::fromCustomer)
                .orElseThrow(()->new RuntimeException("customer not found with id"));
    }

    public CustomerDto  updateCustomer(Long id,CustomerDto customerDto)
    {
        if(!customerRepository.existsById(id))
        {
            throw new RuntimeException("customer not found with id");
        }
        Customer customer=customerDto.toCustomer();
        customer.setId(id);
        customer=customerRepository.save(customer);
        return CustomerDto.fromCustomer(customer);
    }
    public void deleteCustomer(Long id){
        if (!customerRepository.existsById(id))
        {
            throw new RuntimeException("customer not found with id");
        }
        customerRepository.deleteById(id);
    }

    public List<CustomerDto> getAllCustomers(){
        return customerRepository.findAll().stream()
                .map(CustomerDto::fromCustomer)
                .toList();
    }
}
