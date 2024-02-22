package com.project.ecommerce.customer;

import com.project.ecommerce.exception.UserNotFoundException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "api/v1/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("/test")
  public String test() {
    System.out.println("TEST");
    return "TEST";
  }

  @GetMapping
  public List<Customer> getCustomers() {
    return customerService.getCustomers();
  }

  @GetMapping("/{email}")
  public ResponseEntity<Customer> findByEmail(
      @Valid @PathVariable String email) throws UserNotFoundException {
    return ResponseEntity.ok(customerService.findByEmail(email));
  }

  @PostMapping(consumes = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
  })
  public ResponseEntity<String> addCustomer(
      @Valid @RequestBody Customer customer) {
    customerService.addCustomer(customer);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body("Customer added successfully");
  }

  @PutMapping("/{email}")
  @ResponseBody
  public ResponseEntity<Customer> updateCustomer(
      @Valid @PathVariable String email,
      @RequestBody Customer customer) throws UserNotFoundException {
    Customer updatedCustomer = customerService.updatedCustomer(email, customer);

    return ResponseEntity.ok(updatedCustomer);
  }

  @DeleteMapping("/{email}")
  public ResponseEntity<Void> deletCustomer(
      @Valid @PathVariable("email") String email) {
    customerService.deleteCustomer(email);

    return ResponseEntity.noContent().build();
  }
}
