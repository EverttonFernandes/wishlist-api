package api.wishlist.api;

import api.wishlist.application.dto.ProductDTO;
import api.wishlist.application.service.ProductService;
import api.wishlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@ResponseBody
@RestController
@RequestMapping("/products")
public class WishlistApi {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        var getAllProducts = productService.getProducts();
        return ResponseEntity.ok(getAllProducts);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Product>> createProduct(@RequestBody ProductDTO dto) {
        Product newProduct = productService.createProduct(dto);
        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(WishlistApi.class).createProduct(dto)).withSelfRel();
        EntityModel<Product> resource = EntityModel.of(newProduct);
        resource.add(selfLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
}

