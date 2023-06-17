package api.wishlist.domain;

import api.wishlist.domain.businessRules.BusinessRulesForCreateProduct;
import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
        String priceString = price.toString();
        try {
            double parsedPrice = Double.parseDouble(priceString);
            if (parsedPrice < 0) {
                throw new IllegalArgumentException("O preço não pode ser negativo");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O preço deve ser um número válido");
        }
        return price;
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
