package repositories;

import entities.CustomerNode;
import entities.ProductNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerGraphRepository extends Neo4jRepository<CustomerNode, Long> {

    //CRUD

    @Query("CREATE (c:Client {username: $username, email: $email, password: $password, firstName: $firstName, lastName: $lastName}) RETURN c")
    CustomerNode createClient(String username, String email, String password, String firstName, String lastName);

    @Query("MATCH (c:Client {username: $username}) " +
            "SET c.email = $email, c.firstName = $firstName, c.lastName = $lastName " +
            "RETURN c")
    CustomerNode updateClient(String username, String email, String firstName, String lastName);




    @Query("MATCH (c:Client) RETURN c")
    List<CustomerNode> findAllClients();

    @Query("MATCH (c:Client {username: $username}) RETURN c")
    CustomerNode findClientByUsername(String username);

    @Query("MATCH (c:Client) WHERE c.email = $email RETURN c")
    CustomerNode findByEmail(String email);

    @Query("MATCH (c:Client {username: $username}) " +
            "MATCH (c)-[:VIO]->(p:Product) " +
            "RETURN collect(p) AS recentlyViewedProducts")
    List<ProductNode> findRecentlyViewedProducts(String username);

    @Query("MATCH (c:Client {username: $username}) " +
            "RETURN c.purchases AS purchases")
    List<ProductNode> findPurchasesByClient(String username);


    @Query("MATCH (u:User {username: $username}) " +
            "MATCH (u)-[:VIO]->(p:Product) " +
            "RETURN collect(p) AS recentlyViewedProducts")
    List<ProductNode> findRecentlyViewedProductsByUserInGraph(String username);

    @Query("MATCH (p:Product {code: $code}) " +
            "MATCH (c:Client {username: $username}) " +
            "MERGE (c)-[s:VIO]->(p)")
    void addProductToRecentlyViewedInGraph(String username, String code);


    //Productos Favoritos del Cliente
    @Query("MATCH (c:Client {username: $username}), (p:Product {code: $productId}) CREATE (c)-[:FAVORITE]->(p)")
    void addProductToFavoritesInGraph(String username, String productId);

    @Query("MATCH (c:Client {username: $username}) " +
            "MATCH (c)-[:FAVORITE]->(p:Product) " +
            "RETURN collect(p) AS favoriteProducts")
    List<ProductNode> findFavoriteProductsByClientInGraph(String username);

    @Query("MATCH (c:Client {username: $username})-[r:FAVORITE]->(p:Product {code: $productId}) DELETE r")
    void deleteFavoriteProduct(String username, String productId);

}
