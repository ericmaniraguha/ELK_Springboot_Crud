package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Product;
import repo.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;  // Inject a repository for managing products.

    // Retrieve all products from the repository and return them as an iterable.
    public Iterable<Product> getProducts() {
        return productRepo.findAll();
    }

    // Insert a new product into the repository and return the inserted product.
    public Product insertProduct(Product product) {
        return productRepo.save(product);
    }

    // Update an existing product's price by providing a new product and its ID.
    // The method retrieves the existing product, updates its price, and returns the updated product.
    public Product updateProduct(Product product, int id) {
        Product product1 = productRepo.findById(id).get();
        product1.setPrice(product.getPrice());
        return product1;
    }

    // Delete a product from the repository based on its ID.
    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }
}
