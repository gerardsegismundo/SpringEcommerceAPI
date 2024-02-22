package com.project.ecommerce.product;

import java.util.List;
import java.util.Optional;
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

@RequestMapping(path = "api/v1/product")
@RestController
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<Product> getProducts() {
    return productService.getProducts();
  }

  @GetMapping("/{id}")
  public Optional<Product> getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  @PostMapping
  public void addProduct(@RequestBody Product product) {
    productService.addProduct(product);
  }

  @PutMapping("/{id}")
  @ResponseBody
  public ResponseEntity<Product> updateProduct(
      @PathVariable Long id,
      @RequestBody Product product) {
    Product updatedProduct = productService.updateProduct(id, product);

    return ResponseEntity.ok(updatedProduct);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
    productService.deleteProduct(id);

    return ResponseEntity.noContent().build();
  }
}
