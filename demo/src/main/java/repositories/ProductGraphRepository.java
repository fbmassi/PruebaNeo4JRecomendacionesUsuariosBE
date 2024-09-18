package repositories;


import entities.ProductNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductGraphRepository extends Neo4jRepository<ProductNode, UUID> {

    //CRUD

    @Query("CREATE (p:Product {code: $code, name: $name, description: $description, stock: $stock, price: $price, category: $category}) RETURN p")
    ProductNode createProduct(String code, String name, String description, int stock, double price, String category);

    @Query("MATCH (p:Product {code: $code}) " +
            "SET p.name = $name, p.description = $description, p.stock = $stock, p.price = $price, p.category = $category " +
            "RETURN p")
    ProductNode updateProduct(String code, String name, String description, int stock, double price, String category);

    @Query("MATCH (p:Product {code: $code}) DELETE p")
    void deleteProduct(String code);

    //OBTENER INFO

    @Query("MATCH (p:Product) RETURN p")
    List<ProductNode> findAllProducts();

    @Query("MATCH (p:Product {code: $code}) RETURN p")
    ProductNode findProductByCode(String code);

    @Query("MATCH (p:Product {name: $name}) RETURN p")
    ProductNode findProductByName(String name);

    @Query("MATCH (p:Product) WHERE p.highlighted = true RETURN p")
    List<ProductNode> findHighlightedProducts();

    @Query("MATCH (p:Product) RETURN DISTINCT p.category AS category, collect(p) AS products")
    List<ProductNode> findProductsGroupedByCategory();

}
