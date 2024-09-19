package repositories;

import entities.ProductNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductNodeRepository extends Neo4jRepository<ProductNode, UUID> {
    Optional<ProductNode> findById(Long id);
    void deleteById(Long id);
}
