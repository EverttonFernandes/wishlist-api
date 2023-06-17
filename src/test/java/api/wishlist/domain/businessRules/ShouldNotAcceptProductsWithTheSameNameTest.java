package api.wishlist.domain.businessRules;

import api.wishlist.domain.Exceptions.ConflictException;
import api.wishlist.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

class ShouldNotAcceptProductsWithTheSameNameTest {

    @Mock
    private ProductRepository productRepository;

    private ShouldNotAcceptProductsWithTheSameName shouldNotAcceptProductsWithTheSameName;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        shouldNotAcceptProductsWithTheSameName = new ShouldNotAcceptProductsWithTheSameName(productRepository);
    }

    @Test
    void shouldVerifyIfProductNameIsValid() {
        BDDMockito.given(productRepository.existsByName(anyString())).willReturn(false);

        shouldNotAcceptProductsWithTheSameName.nameIsValid(anyString());

        verify(productRepository, Mockito.times(1)).existsByName(anyString());
    }

    @Test
    void shouldThrowConflictExceptionWhenProductAlreadyExists() {
        String productName = "Smartwatch";
        BDDMockito.given(productRepository.existsByName(productName)).willReturn(true);

        Assertions.assertThrows(ConflictException.class, () -> {
            shouldNotAcceptProductsWithTheSameName.nameIsValid(productName);
        });

        verify(productRepository, Mockito.times(1)).existsByName(productName);
    }
}
