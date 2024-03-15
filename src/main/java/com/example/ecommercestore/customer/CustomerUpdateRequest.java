package com.example.ecommercestore.customer;

public record CustomerUpdateRequest (
        String name,
        String email,
        Integer age
) {
}
