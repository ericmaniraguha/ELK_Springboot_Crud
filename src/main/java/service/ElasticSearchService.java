package service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import entity.Product;
import util.ElasticSearchUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;  // Inject an Elasticsearch client.

    // This method performs a simple Elasticsearch matchAll query and returns search results.
    public SearchResponse<Map> matchAllServices() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s.query(supplier.get()), Map.class);
        System.out.println("elasticsearch query is " + supplier.get().toString());
        return searchResponse;
    }

    // This method performs a matchAll query specifically for 'Product' entities.
    public SearchResponse<Product> matchAllProductsServices() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("products").query(supplier.get()), Product.class);
        System.out.println("elasticsearch query is " + supplier.get().toString());
        return searchResponse;
    }

    // This method performs a boolean query using the provided product name and quantity parameters.
    public SearchResponse<Product> boolQueryImpl(String productName, Integer qty) throws IOException {
        // Create a supplier for a boolean query with product name and quantity.
        Supplier<Query> supplier = ElasticSearchUtil.supplierQueryForBoolQuery(productName, qty);
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("products").query(supplier.get()), Product.class);
        System.out.println("elasticsearch query is " + supplier.get().toString());
        return searchResponse;
    }
}
