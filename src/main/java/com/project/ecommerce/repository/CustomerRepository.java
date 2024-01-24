package com.project.ecommerce.repository;

import com.project.ecommerce.model.Customer;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
  Optional<Customer> findByEmail(String email);
}
