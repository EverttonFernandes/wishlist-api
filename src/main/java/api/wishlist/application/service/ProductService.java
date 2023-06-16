package api.wishlist.application.service;

import api.wishlist.domain.Product;
import org.bson.types.Decimal128;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

//    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product("1", "TV", Decimal128.parse("3000"));
        products.add(product);
        return products;
    }

}
