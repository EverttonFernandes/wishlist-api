package api.wishlist.domain.businessRules;

import api.wishlist.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class BusinessRulesForCreateProduct {

    private final ShouldNotAcceptProductsWithTheSameName shouldNotAcceptProductsWithTheSameName;
    private final ShouldNotHaveMoreThanTwentyProducts shouldNotHaveMoreThanTwentyProducts;

    public BusinessRulesForCreateProduct(ShouldNotAcceptProductsWithTheSameName shouldNotAcceptProductsWithTheSameName, ShouldNotHaveMoreThanTwentyProducts shouldNotHaveMoreThanTwentyProducts) {
        this.shouldNotAcceptProductsWithTheSameName = shouldNotAcceptProductsWithTheSameName;
        this.shouldNotHaveMoreThanTwentyProducts = shouldNotHaveMoreThanTwentyProducts;
    }

    public void validateNewProduct(Product newProduct) {
        shouldNotAcceptProductsWithTheSameName.nameIsValid(newProduct.getName());
        shouldNotHaveMoreThanTwentyProducts.hasTwentyProducts();
    }
}
