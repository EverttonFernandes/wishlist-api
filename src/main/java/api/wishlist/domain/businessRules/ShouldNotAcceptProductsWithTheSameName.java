package api.wishlist.domain.businessRules;

import api.wishlist.domain.Exceptions.ConflictException;
import api.wishlist.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ShouldNotAcceptProductsWithTheSameName {

    private final ProductRepository productRepository;

    public ShouldNotAcceptProductsWithTheSameName(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void nameIsValid(String productName) {
        var existingProduct = productRepository.existsByName(productName);
        if (existingProduct) {
            throw new ConflictException("The product you are trying to add already exists");
        }
    }
}
