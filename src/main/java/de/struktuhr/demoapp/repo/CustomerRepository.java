package de.struktuhr.demoapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.struktuhr.demoapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}