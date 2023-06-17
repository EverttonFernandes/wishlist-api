package api.wishlist.domain.businessRules;

import api.wishlist.domain.Exceptions.BadRequestException;
import api.wishlist.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class ShouldNotHaveMoreThanTwentyProductsTest {

    @Mock
    private ProductRepository productRepository;

    private ShouldNotHaveMoreThanTwentyProducts shouldNotHaveMoreThanTwentyProducts;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        shouldNotHaveMoreThanTwentyProducts = new ShouldNotHaveMoreThanTwentyProducts(productRepository);
    }

    @Test
    void shouldAcceptANewProductWhenTheListHasLessThanTwentyProducts() {
        BDDMockito.given(productRepository.count()).willReturn(19L);
        shouldNotHaveMoreThanTwentyProducts.hasTwentyProducts();
        verify(productRepository, Mockito.times(1)).count();
    }

    @Test
    void shouldNotAcceptANewProductWhenTheListIsFull() {
        BDDMockito.given(productRepository.count()).willReturn(20L);

        Assertions.assertThrows(BadRequestException.class, () -> {
            shouldNotHaveMoreThanTwentyProducts.hasTwentyProducts();
        });

        verify(productRepository, Mockito.times(1)).count();
    }
}
