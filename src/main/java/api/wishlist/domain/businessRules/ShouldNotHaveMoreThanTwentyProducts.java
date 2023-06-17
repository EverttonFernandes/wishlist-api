package api.wishlist.domain.businessRules;

import api.wishlist.domain.Exceptions.BadRequestException;
import api.wishlist.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ShouldNotHaveMoreThanTwentyProducts {
    private final ProductRepository productRepository;

    public ShouldNotHaveMoreThanTwentyProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void hasTwentyProducts() {
        var quantityOfProducts = productRepository.count();
        if (quantityOfProducts >= 20) {
            throw new BadRequestException("It is not possible to add new products to the list, as the maximum limit of 20 products has been reached");
        }
    }
}
