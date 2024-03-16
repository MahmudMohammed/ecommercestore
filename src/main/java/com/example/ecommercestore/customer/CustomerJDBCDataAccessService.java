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
        var sql = """
                SELECT count(id) 
                FROM customer
                WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
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
        var sql = """
                SELECT count(id) 
                FROM customer
                WHERE id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;

    }

    @Override
    public void deleteCustomerById(Integer id) {
        var sql = """
                DELETE FROM customer
                WHERE id = ?
                """;
        int update = jdbcTemplate.update(sql, id);
        System.out.println("deleted customer by id " + update);
    }

    @Override
    public void updateCustomer(Customer update) {

    }
}
