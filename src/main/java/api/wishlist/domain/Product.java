package api.wishlist.domain;

import api.wishlist.domain.businessRules.BusinessRulesForCreateProduct;
import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Document
public class Product {

    @Id
    private String id;

    private String name;

    private Decimal128 price;

    public Product() {
    }

    public Product(String id, String name, Decimal128 price) {
        this.id = id;
        this.name = name;
        this.price = this.validatePrice(price);
    }

    private Decimal128 validatePrice(Decimal128 price) {
        if (price.bigDecimalValue().doubleValue() < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo");
        }

        if (!isMonetaryValue(price.toString())) {
            throw new IllegalArgumentException("O preço deve ser um valor monetário válido");
        }
        return price;
    }

    private boolean isMonetaryValue(String value) {
        try {
            BigDecimal parsedValue = new BigDecimal(value);
            return parsedValue.compareTo(BigDecimal.ZERO) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void validateProduct(BusinessRulesForCreateProduct businessRulesForCreateProduct) {
        businessRulesForCreateProduct.validateNewProduct(this);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Decimal128 getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product that = (Product) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice());
    }
}
