package api.wishlist.domain.businessRules;

import api.wishlist.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class BusinessRulesForCreateProductTest {

    @Mock
    private ShouldNotAcceptProductsWithTheSameName shouldNotAcceptProductsWithTheSameName;

    @Mock
    private ShouldNotHaveMoreThanTwentyProducts shouldNotHaveMoreThanTwentyProducts;

    private BusinessRulesForCreateProduct businessRulesForCreateProduct;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        businessRulesForCreateProduct = new BusinessRulesForCreateProduct(shouldNotAcceptProductsWithTheSameName, shouldNotHaveMoreThanTwentyProducts);
    }

    @ParameterizedTest
    @MethodSource("api.wishlist.fixture.ProductFixture#buildNewProduct")
    void shouldVerifyIfHaveMoreThanTwentyProducts(Product product) {
        businessRulesForCreateProduct.validateNewProduct(product);
        verify(shouldNotHaveMoreThanTwentyProducts, Mockito.times(1)).hasTwentyProducts();
    }

    @ParameterizedTest
    @MethodSource("api.wishlist.fixture.ProductFixture#buildNewProduct")
    void shouldCheckThatThereAreNoDuplicateProducts(Product product) {
        businessRulesForCreateProduct.validateNewProduct(product);
        verify(shouldNotAcceptProductsWithTheSameName, Mockito.times(1)).nameIsValid(product.getName());
    }
}
