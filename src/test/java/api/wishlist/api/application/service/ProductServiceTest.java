package api.wishlist.api.application.service;

import api.wishlist.application.converter.ProductDTOConverter;
import api.wishlist.application.dto.ProductDTO;
import api.wishlist.application.service.ProductService;
import api.wishlist.domain.Product;
import api.wishlist.infrastructure.repository.ProductRepository;
import org.bson.types.Decimal128;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductDTOConverter productDTOConverter;

    @BeforeEach
    public void setup() {
        openMocks(this);
        productService = new ProductService(productRepository, productDTOConverter);
    }

    @Test
    void shouldGetAllProducts() {
        List<Product> products = new ArrayList<>();
        given(productRepository.findAll()).willReturn(products);
        productService.getProducts();
        verify(productRepository, Mockito.times(1)).findAll();
    }

    @ParameterizedTest
    @MethodSource("api.wishlist.fixture.ProductDTOFixture#buildProduct")
    void shouldCreateProduct(ProductDTO dto) {
        var newProduct = new Product(null, dto.getName(), Decimal128.parse(dto.getPrice()));
        given(productDTOConverter.convertDTO(dto)).willReturn(newProduct);
        productService.createProduct(dto);
        verify(productRepository, Mockito.times(1)).save(newProduct);
    }
}
