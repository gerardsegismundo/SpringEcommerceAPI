package com.project.ecommerce.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", updatable = false, nullable = false)
  private Long id;

  @NonNull
  @NotBlank(message = "Name is required.")
  private String name;

  @NotBlank(message = "Price is required.")
  private BigDecimal price;

  @OneToMany(
    mappedBy = "product",
    orphanRemoval = true,
    cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
    fetch = FetchType.LAZY
  )
  private List<Review> reviews = new ArrayList<>();

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
