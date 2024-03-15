package com.example.ecommercestore.customer;

import com.example.ecommercestore.exception.DuplicateResourceException;
import com.example.ecommercestore.exception.RequestValidationException;
import com.example.ecommercestore.exception.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDao;

    public CustomerService(CustomerDAO customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomer(){
        return customerDao.getAllCustomer();
    }

    public Customer getCustomerById(Integer id){
        return customerDao.getCustomerById(id)
                .orElseThrow(
                        ()-> new ResourceNotFound(
                                "customer with id [%s] not found".formatted(id))
                );
    }

    public void addCustomer(CustomerRegisterationRequest customerRegisterationRequest){

        String email = customerRegisterationRequest.email();

        if( customerDao.existPersonWithEmail(email) ){
            throw new DuplicateResourceException("Email already taken");
        }

        Customer customer = new Customer(
                        customerRegisterationRequest.name(),
                        customerRegisterationRequest.email(),
                        customerRegisterationRequest.age()
        );
        customerDao.insertCustomer(customer);
    }

    public void deleteCustomerById(Integer id){
        if (!customerDao.existPersonById(id)){
            throw new ResourceNotFound(
                    "customer with id [%s] not found".formatted(id)
            );
        }
        customerDao.deleteCustomerById(id);
    }
    public void updateCustomer(Integer id , CustomerUpdateRequest updateRequest){

        Customer customer = getCustomerById(id);

        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())){
            customer.setName(updateRequest.name());
            changes = true;
        }
        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())){
            customer.setAge(updateRequest.age());
            changes = true;
        }
        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())){
            if( customerDao.existPersonWithEmail(updateRequest.email()) ){
                throw new DuplicateResourceException("Email already taken");
            }
            customer.setEmail(updateRequest.email());
            changes = true;
        }
        if (!changes){
            throw new RequestValidationException("No data changes found !");
        }
        customerDao.updateCustomer(customer);

    }
}
