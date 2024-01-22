package com.project.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id", updatable = false, nullable = false)
  private Long id;

  @OneToOne
  // @PrimaryKeyJoinColumn
  private Customer customer;

  @ManyToOne
  @JoinColumn(
    name = "product_id",
    nullable = false,
    referencedColumnName = "product_id",
    foreignKey = @ForeignKey(name = "review_fk")
  )
  private Product product;

  @Column(nullable = false)
  private String comment;
}
