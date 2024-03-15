package com.example.ecommercestore.customer;

public record CustomerRegisterationRequest(
        String name,
        String email,
        Integer age
) {


}
