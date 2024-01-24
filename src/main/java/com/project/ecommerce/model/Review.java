package com.project.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id", updatable = false, nullable = false)
  private Long id;

  @ManyToOne
  @NotNull
  @MapsId("customer")
  @JoinColumn(
    name = "email",
    nullable = false,
    referencedColumnName = "email",
    foreignKey = @ForeignKey(name = "customer_review_fk")
  )
  private Customer customer;

  @NotNull(message = "Rating is required.")
  @Max(value = 5, message = "Rating must be at most 5.")
  private Integer rating;

  @ManyToOne
  @MapsId("product")
  @JoinColumn(
    name = "product_id",
    nullable = false,
    referencedColumnName = "product_id",
    foreignKey = @ForeignKey(name = "product_review_fk")
  )
  @NotNull
  private Product product;

  @Column(nullable = false)
  @NotBlank(message = "Comment is required.")
  private String comment;
}
