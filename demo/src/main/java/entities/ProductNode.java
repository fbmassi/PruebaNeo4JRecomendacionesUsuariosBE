package entities;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class ProductNode {
    @Id
    private Long id;
    private String name;
    // ... otras propiedades

    @Relationship(type = "FAVORITO_DE", direction = Relationship.Direction.INCOMING)
    private Set<CustomerNode> favoritos = new HashSet<>();
}