package com.project.ecommerce.customer;

import com.project.ecommerce.exception.UserNotFoundException;

import java.util.List;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<Customer> getCustomers() {
    return (List<Customer>) customerRepository.findAll();
  }

  public Customer findByEmail(@NonNull String email)
      throws UserNotFoundException {
    return customerRepository
        .findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("Customer not found with email: " + email));
  }

  public void addCustomer(@NonNull Customer customer) {
    customerRepository.save(customer);
  }

  // @SuppressWarnings("null")
  @SuppressWarnings("null")
  public Customer updatedCustomer(
      @NonNull String email,
      @NonNull Customer customer) throws UserNotFoundException {
    return customerRepository
        .findById(email)
        .map(c -> {
          if (customer.getName() != null) {
            c.setName(customer.getName());
          }

          if (customer.getAddress() != null) {
            c.setAddress(customer.getAddress());
          }

          return customerRepository.save(c);
        })
        .orElseThrow(() -> new UserNotFoundException("Customer not found with email: " + email));
  }

  @SuppressWarnings("null")
  public Customer updatsedCustomer(
      @NonNull String email,
      @NonNull Customer customer) throws UserNotFoundException {
    return customerRepository
        .findById(email)
        .map(existingCustomer -> {
          if (customer.getName() != null) {
            existingCustomer.setName(customer.getName());
          }

          if (customer.getAddress() != null) {
            existingCustomer.setAddress(customer.getAddress());
          }

          return customerRepository.save(existingCustomer);
        })
        .orElseThrow(() -> new UserNotFoundException("Customer not found with email: " + email));
  }

  public void deleteCustomer(@NonNull String email) {
    customerRepository.deleteById(email);
  }
}
