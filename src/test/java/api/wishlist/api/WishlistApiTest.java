package api.wishlist.api;

import api.wishlist.application.dto.ProductDTO;
import api.wishlist.application.service.ProductService;
import api.wishlist.domain.Product;
import org.bson.types.Decimal128;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
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

    @ParameterizedTest
    @MethodSource("api.wishlist.fixture.ProductDTOFixture#buildProductList")
    void shouldGetAllProductsInWishlist(List<Product> products) {
        given(productService.getProducts()).willReturn(products);
        var response = wishlistApi.getAllProducts();

        verify(productService, Mockito.times(1)).getProducts();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @ParameterizedTest
    @MethodSource("api.wishlist.fixture.ProductDTOFixture#buildProduct")
    void shouldAddNewProductInWishlist(ProductDTO dto) {
        given(productService.createProduct(dto)).willReturn(new Product(null, dto.getName(), Decimal128.parse(dto.getPrice())));
        var response = wishlistApi.createProduct(dto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(productService, Mockito.times(1)).createProduct(dto);
    }

    @ParameterizedTest
    @MethodSource("api.wishlist.fixture.ProductFixture#buildNewProduct")
    void shouldGetProductWhenInformedInRequest(Product product) {
        given(productService.getProductById(product.getId())).willReturn(Optional.of(product));
        var response = wishlistApi.getProductById(product.getId());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(productService, Mockito.times(1)).getProductById(product.getId());
    }

    @Test
    void shouldDeleteProductWhenInformedInRequest() {
        given(productService.deleteProductById(anyString())).willReturn(true);
        var response = wishlistApi.deleteProductById(anyString());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(productService, Mockito.times(1)).deleteProductById(anyString());
    }
}
