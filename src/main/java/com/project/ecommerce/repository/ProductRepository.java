package com.project.ecommerce.repository;

import com.project.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.List;
// import lombok.NonNull;
// import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  // @SuppressWarnings("null")
  // @Query("SELECT p FROM Product p")
  // List<@NonNull Product> findAll();
}
