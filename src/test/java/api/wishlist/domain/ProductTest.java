package api.wishlist.domain;

import api.wishlist.domain.businessRules.BusinessRulesForCreateProduct;
import org.bson.types.Decimal128;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class ProductTest {

    @Mock
    private BusinessRulesForCreateProduct businessRulesForCreateProduct;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldValidateDomainRules() {
        var newProduct = new Product("1L", "Playstation 5", Decimal128.parse("4000"));
        newProduct.validateProduct(businessRulesForCreateProduct);
        verify(businessRulesForCreateProduct, Mockito.times(1)).validateNewProduct(newProduct);
    }

    @Test
    void shouldValidateIfPriceIsMonetaryValue() {
        String validPrice = "10.99";
        String invalidPrice = "abc";

        Assertions.assertDoesNotThrow(() -> new Product("1", "Product 1", Decimal128.parse(validPrice)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> createProductWithInvalidPrice("2", "Product 2", invalidPrice));
    }

    private void createProductWithInvalidPrice(String id, String name, String price) {
        new Product(id, name, Decimal128.parse(price));
    }

}
