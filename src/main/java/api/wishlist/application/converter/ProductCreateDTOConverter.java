package api.wishlist.application.converter;

import api.wishlist.application.dto.ProductDTO;
import api.wishlist.domain.Product;
import api.wishlist.domain.businessRules.BusinessRulesForCreateProduct;
import org.bson.types.Decimal128;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateDTOConverter implements ConverterDTO {

    private BusinessRulesForCreateProduct businessRulesForCreateProduct;

    public ProductCreateDTOConverter(BusinessRulesForCreateProduct businessRulesForCreateProduct) {
        this.businessRulesForCreateProduct = businessRulesForCreateProduct;
    }

    @Override
    public Product convertDTO(ProductDTO productDTO) {
        Product newProduct = new Product(null, productDTO.getName(), Decimal128.parse(productDTO.getPrice()));
        businessRulesForCreateProduct.validateNewProduct(newProduct);
        return newProduct;
    }
}
