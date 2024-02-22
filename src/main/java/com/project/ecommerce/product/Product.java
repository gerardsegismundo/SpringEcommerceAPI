package com.project.ecommerce.product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", updatable = false)
  private Long id;

  @NotBlank(message = "Name is required.")
  private String name;

  @NotBlank(message = "Price is required.")
  private BigDecimal price;

  @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = { CascadeType.PERSIST,
      CascadeType.REMOVE }, fetch = FetchType.EAGER)

  private List<Review> reviews = new ArrayList<>();
}
