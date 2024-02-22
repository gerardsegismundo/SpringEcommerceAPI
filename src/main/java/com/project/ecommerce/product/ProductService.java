package com.project.ecommerce.product;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@SuppressWarnings("null")
@Service
public class ProductService {

  private final ProductRepository productRepository;

  ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getProducts() {
    return (List<Product>) productRepository.findAll();
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  public void addProduct(Product product) {
    productRepository.save(product);
  }

  public Product updateProduct(Long id, Product product) {
    return productRepository
        .findById(id)
        .map(p -> {
          if (product.getName() != null) {
            p.setName(product.getName());
          }

          if (product.getPrice() != null) {
            p.setPrice(product.getPrice());
          }
          return productRepository.save(p);
        })
        .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
