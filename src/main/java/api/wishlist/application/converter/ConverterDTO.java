package api.wishlist.application.converter;

import api.wishlist.application.dto.ProductDTO;
import api.wishlist.domain.Product;

public interface ConverterDTO {
    Product convertDTO(ProductDTO productDTO);
}
