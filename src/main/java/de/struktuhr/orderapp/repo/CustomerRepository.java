package de.struktuhr.orderapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.struktuhr.orderapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}