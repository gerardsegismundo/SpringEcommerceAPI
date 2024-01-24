package com.project.ecommerce.repository;

import com.project.ecommerce.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// import java.util.List;
// import lombok.NonNull;
// import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {}
