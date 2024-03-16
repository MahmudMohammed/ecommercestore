package com.example.ecommercestore.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository("jdbc")
public class CustomerJDBCDataAccessService implements CustomerDAO{

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate, CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public List<Customer> getAllCustomer() {
        var sql = """
                SELECT * FROM customer
                """;

        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        var sql = """
                SELECT id, name , email, age
                FROM customer
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql ,  customerRowMapper , id)
                .stream()
                .findFirst();
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return false;
    }

    @Override
    public void insertCustomer(Customer customer) {
        var sql = """
                INSERT INTO customer(name, email, age)
                VALUES (?,?,?)
                """;
        int update = jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAge()
        );
        System.out.println("JDBC Insert " + update );
    }

    @Override
    public boolean existPersonById(Integer id) {
        return false;
    }

    @Override
    public void deleteCustomerById(Integer id) {

    }

    @Override
    public void updateCustomer(Customer update) {

    }
}
