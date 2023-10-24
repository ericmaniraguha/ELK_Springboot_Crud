package repo;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import entity.Product;

public interface ProductRepo extends ElasticsearchRepository<Product,Integer> {



        }