package api.wishlist.application.service;

import api.wishlist.application.converter.ProductDTOConverter;
import api.wishlist.application.dto.ProductDTO;
import api.wishlist.domain.Product;
import api.wishlist.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductDTOConverter productDTOConverter;

    public ProductService(ProductRepository productRepository, ProductDTOConverter productDTOConverter) {
        this.productRepository = productRepository;
        this.productDTOConverter = productDTOConverter;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductDTO dto) {
        var newProduct = productDTOConverter.convertDTO(dto);
        productRepository.save(newProduct);
        return newProduct;
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public boolean deleteProductById(String id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
