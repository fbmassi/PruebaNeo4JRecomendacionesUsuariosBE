package repositories;


import entities.ProductNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGraphRepository extends Neo4jRepository<ProductNode, Long> {

    // Crear un nuevo producto
    @Query("CREATE (p:Product {id: $id, name: $name, category: $category, year: $year, director: $director}) RETURN p")
    ProductNode createProductNode(Long id, String name, String category, int year, String director);

    // Actualizar un producto
    @Query("MATCH (p:Product {id: $id}) " +
            "SET p.name = $name, p.category = $category, p.year = $year, p.director = $director " +
            "RETURN p")
    ProductNode updateProductNode(Long id, String name, String category, int year, String director);

    // Eliminar un producto
    @Query("MATCH (p:Product {id: $id}) DELETE p")
    void deleteProductNode(Long id);

    // Buscar todos los productos
    @Query("MATCH (p:Product) RETURN p")
    List<ProductNode> findAllProductNodes();

    // Buscar producto por id
    @Query("MATCH (p:Product {id: $id}) RETURN p")
    ProductNode findProductNodeById(Long id);

    // Buscar producto por nombre
    @Query("MATCH (p:Product {name: $name}) RETURN p")
    ProductNode findProductNodeByName(String name);

    // Buscar productos destacados
    @Query("MATCH (p:Product) WHERE p.highlighted = true RETURN p")
    List<ProductNode> findHighlightedProductNodes();

    // Agrupación de productos por categoría
    @Query("MATCH (p:Product) RETURN DISTINCT p.category AS category, collect(p) AS products")
    List<ProductNode> findProductNodesGroupedByCategory();
}