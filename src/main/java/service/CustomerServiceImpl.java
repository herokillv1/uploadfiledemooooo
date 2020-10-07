package service;

import model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService {
    private static List<Customer> customers;

    static {

        customers = new ArrayList<>();
        customers.add(new  Customer(1, "Rick Hiệp", "hiep@codegym.vn", "Hanoi","a.jpg"));

    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public void save(Customer customer) {

        customers.add(customer);
    }

}
