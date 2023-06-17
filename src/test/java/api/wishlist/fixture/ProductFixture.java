package api.wishlist.fixture;

import api.wishlist.domain.Product;
import org.bson.types.Decimal128;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ProductFixture {

    public static Stream<Arguments> buildNewProduct() {
        return Stream.of(
                Arguments.of(new Product("648c6d36da518f04371792fd", "Mortal Kombat 1", Decimal128.parse("469.90")))
        );
    }

}
