package com.example.ecommercestore.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    List<Customer> getAllCustomer();

    Optional<Customer> getCustomerById(Integer id);

    boolean existPersonWithEmail(String email);

    void insertCustomer(Customer customer);

    boolean existPersonById(Integer id);

    void deleteCustomerById(Integer id);

    void updateCustomer(Customer update);

}
