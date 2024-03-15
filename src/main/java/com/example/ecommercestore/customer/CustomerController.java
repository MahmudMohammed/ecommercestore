package com.example.ecommercestore.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/add")
    public void insertCustomer(@RequestBody CustomerRegisterationRequest request){
        customerService.addCustomer(request);
    }
    @DeleteMapping("{id}")
    void deleteCustomerById(@PathVariable Integer id){
        customerService.deleteCustomerById(id);
    }
    @PutMapping("{id}")
    public void deleteCustomerById(
            @PathVariable Integer id,
            @RequestBody CustomerUpdateRequest UpdateRequest
    ){
        customerService.updateCustomer(id , UpdateRequest);
    }
}
