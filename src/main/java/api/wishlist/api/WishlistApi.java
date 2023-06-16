package api.wishlist.api;

import api.wishlist.application.service.ProductService;
import api.wishlist.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("wishlist")
public class WishlistApi {

    private final ProductService productService;

    public WishlistApi(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        var getAllProducts = productService.getProducts();
        return ResponseEntity.ok(getAllProducts);
    }
}

