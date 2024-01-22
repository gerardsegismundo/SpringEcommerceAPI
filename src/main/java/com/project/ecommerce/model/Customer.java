package com.project.ecommerce.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  @Id
  @GeneratedValue
  @Column(name = "customer_id", updatable = false, nullable = false)
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(
    mappedBy = "customer",
    orphanRemoval = true,
    cascade = { CascadeType.PERSIST, CascadeType.REMOVE }
  )
  private List<Review> reviews = new ArrayList<>();
}
