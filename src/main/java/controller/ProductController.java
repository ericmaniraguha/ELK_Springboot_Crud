package controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import entity.Product;
import service.ElasticSearchService;
import service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apis")
public class ProductController {

    @Autowired
    private ProductService productService;  // Inject a service for managing products.

    @Autowired
    private ElasticSearchService elasticSearchService;  // Inject a service for Elasticsearch queries.

    // Handle GET request to retrieve all products.
    @GetMapping("/findAll")
    Iterable<Product> findAll(){
       return productService.getProducts();
    }

    // Handle POST request to insert a new product.
    @PostMapping("/insert")
    public Product insertProduct(@RequestBody Product product){
       return productService.insertProduct(product);
    }

    // Handle GET request to perform an Elasticsearch matchAll query and return the search results.
    @GetMapping("/matchAll")
    public String matchAll() throws IOException {
        SearchResponse<Map> searchResponse = elasticSearchService.matchAllServices();
        System.out.println(searchResponse.hits().hits().toString());
        return searchResponse.hits().hits().toString();
    }

    // Handle GET request to perform an Elasticsearch matchAll query for products and return a list of products.
    @GetMapping("/matchAllProducts")
    public List<Product> matchAllProducts() throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchAllProductsServices();
        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        List<Product> listOfProducts = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }

    // Handle GET request to perform a boolean query based on product name and quantity and return a list of products.
    @GetMapping("/boolQuery/{productName}/{qty}")
    public List<Product> boolQuery(@PathVariable String productName, @PathVariable Integer qty) throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.boolQueryImpl(productName, qty);
        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        List<Product> listOfProducts = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }
}
