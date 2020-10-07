package service;

import model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    void save(Customer customer);


}
