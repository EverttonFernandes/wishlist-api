package api.wishlist.application.service;

import api.wishlist.application.converter.ProductCreateDTOConverter;
import api.wishlist.application.dto.ProductDTO;
import api.wishlist.domain.Product;
import api.wishlist.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductCreateDTOConverter productCreateDTOConverter;

    public ProductService(ProductRepository productRepository, ProductCreateDTOConverter productCreateDTOConverter) {
        this.productRepository = productRepository;
        this.productCreateDTOConverter = productCreateDTOConverter;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductDTO dto) {
        var newProduct = productCreateDTOConverter.convertDTO(dto);
        productRepository.save(newProduct);
        return newProduct;
    }
}
