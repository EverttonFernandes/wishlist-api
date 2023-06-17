package api.wishlist.api;

import api.wishlist.application.dto.ProductDTO;
import api.wishlist.application.service.ProductService;
import api.wishlist.domain.Product;
import org.bson.types.Decimal128;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class WishlistApiTest {

    @InjectMocks
    private WishlistApi wishlistApi;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    static Stream<Arguments> productProvider() {
        return Stream.of(
                Arguments.of(new ProductDTO("Cadeira gamer", "2500"))
        );
    }

    @Test
    void shouldGetAllProductsInWishlist() {
        List<Product> products = new ArrayList<>();
        Product productOne = new Product("1", "Xbox one", Decimal128.parse("2000"));
        Product productTwo = new Product("1", "Controle Xbox one", Decimal128.parse("300"));
        products.add(productOne);
        products.add(productTwo);
        given(productService.getProducts()).willReturn(products);
        var response = wishlistApi.getAllProducts();

        verify(productService, Mockito.times(1)).getProducts();
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @ParameterizedTest
    @MethodSource("productProvider")
    void shouldAddNewProductInWishlist(ProductDTO dto) {
        given(productService.createProduct(dto)).willReturn(new Product(null, dto.getName(), Decimal128.parse(dto.getPrice())));
        var response = wishlistApi.createProduct(dto);

        Assertions.assertEquals(201, response.getStatusCodeValue());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(productService, Mockito.times(1)).createProduct(dto);
    }
}
