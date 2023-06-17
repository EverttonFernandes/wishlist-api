package api.wishlist.fixture;

import api.wishlist.application.dto.ProductDTO;
import api.wishlist.domain.Product;
import org.bson.types.Decimal128;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class ProductDTOFixture {

    public static Stream<Arguments> buildProduct() {
        return Stream.of(
                Arguments.of(new ProductDTO("Cadeira gamer", "2500"))
        );
    }

    public static Stream<Arguments> buildProductList() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Product("1", "Xbox one", Decimal128.parse("2000")),
                                new Product("2", "Controle Xbox one", Decimal128.parse("300"))
                        )
                )
        );
    }

}
