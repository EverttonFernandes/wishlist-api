package api.wishlist.infrastructure.repository;

import api.wishlist.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(value = "{'name' : ?0}", exists = true)
    boolean existsByName(String name);
}
