package repositories;

import entities.CustomerNode;
import entities.ProductNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerGraphRepository extends Neo4jRepository<CustomerNode, Long> {
    // Crear cliente
    @Query("CREATE (c:Customer {id: $id, firstName: $firstName, lastName: $lastName}) RETURN c")
    CustomerNode createCustomerNode(Long id, String firstName, String lastName);

    // Actualizar cliente
    @Query("MATCH (c:Customer {id: $id}) " +
            "SET c.firstName = $firstName, c.lastName = $lastName " +
            "RETURN c")
    CustomerNode updateCustomerNode(Long id, String firstName, String lastName);

    // Método para eliminar un nodo de cliente por ID
    void deleteCustomerNode(Long id, String firstName, String lastName);

    // Encontrar todos los clientes
    @Query("MATCH (c:Customer) RETURN c")
    List<CustomerNode> findAllCustomerNodes();

    // Encontrar cliente por ID
    @Query("MATCH (c:Customer {id: $id}) RETURN c")
    CustomerNode findCustomerNodeById(Long id);

    // Productos recientemente vistos por cliente
    @Query("MATCH (c:Customer {id: $id}) " +
            "MATCH (c)-[:VIO]->(p:Product) " +
            "RETURN collect(p) AS recentlyViewedProducts")
    List<ProductNode> findRecentlyViewedProductsByCustomerNode(Long id);

    // Agregar producto a los recientemente vistos
    @Query("MATCH (p:Product {id: $productId}), (c:Customer {id: $customerId}) " +
            "MERGE (c)-[r:VIO]->(p)")
    void addProductToRecentlyViewed(Long customerId, Long productId);

    // Recomendaciones por categoría (género)
    @Query("MATCH (c:Customer {id: $customerId}) " +
            "MATCH (c)-[:VIO]->(p:Product) " +
            "WITH DISTINCT p.category AS category " +
            "MATCH (other:Product) " +
            "WHERE other.category = category AND NOT (c)-[:VIO]->(other) " +
            "RETURN DISTINCT other")
    List<ProductNode> recommendProductsByCategory(Long customerId);

    // Recomendaciones por década de lanzamiento
    @Query("MATCH (c:Customer {id: $customerId}) " +
            "MATCH (c)-[:VIO]->(p:Product) " +
            "WITH DISTINCT p.year AS year " +
            "MATCH (other:Product) " +
            "WHERE other.year / 10 = year / 10 AND NOT (c)-[:VIO]->(other) " +
            "RETURN DISTINCT other")
    List<ProductNode> recommendProductsByDecade(Long customerId);

    // Recomendaciones por director
    @Query("MATCH (c:Customer {id: $customerId}) " +
            "MATCH (c)-[:VIO]->(p:Product) " +
            "WITH DISTINCT p.director AS director " +
            "MATCH (other:Product) " +
            "WHERE other.director = director AND NOT (c)-[:VIO]->(other) " +
            "RETURN DISTINCT other")
    List<ProductNode> recommendProductsByDirector(Long customerId);

    // Agregar producto a favoritos
    @Query("MATCH (c:Customer {id: $customerId}), (p:Product {id: $productId}) " +
            "CREATE (c)-[:FAVORITO]->(p)")
    void addProductToFavorites(Long customerId, Long productId);

    // Encontrar productos favoritos
    @Query("MATCH (c:Customer {id: $customerId}) " +
            "MATCH (c)-[:FAVORITO]->(p:Product) " +
            "RETURN collect(p) AS favoriteProducts")
    List<ProductNode> findFavoriteProducts(Long customerId);

    // Eliminar producto de favoritos
    @Query("MATCH (c:Customer {id: $customerId})-[r:FAVORITO]->(p:Product {id: $productId}) DELETE r")
    void deleteFavoriteProduct(Long customerId, Long productId);
}