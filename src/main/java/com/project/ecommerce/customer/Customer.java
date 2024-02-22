package com.project.ecommerce.customer;

import java.util.ArrayList;
import java.util.List;

import com.project.ecommerce.product.Review;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

  @Id
  @Column(unique = true)
  @NotBlank(message = "Email is required.")
  @Email(message = "Invalid email format")
  private String email;

  @NotBlank(message = "Name is required.")
  @Size(min = 3, max = 30, message = "Name must be not be less than 3 or more than 30 characters.")
  private String name;

  @NotBlank(message = "Address is required.")
  private String address;

  @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
  private List<Review> reviews = new ArrayList<>();
}
