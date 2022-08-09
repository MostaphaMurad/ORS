package com.ors.Services;

import com.ors.Models.Customer;
import com.ors.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {
    @Autowired private CustomerRepository customerRepository;
    public Customer getCustomerByEmail(String loggedEmail) {
        Customer customer=customerRepository.findByEmail((loggedEmail));
        return customer;
    }

    public Customer addNewCustomer(Customer customer) {
        Customer savedCustomer=customerRepository.save(customer);
        return savedCustomer;
    }
}
